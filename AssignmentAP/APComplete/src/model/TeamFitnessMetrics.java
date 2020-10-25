package model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import handler.TeamHandler;
import handler.ProjectHandler;
import handler.PreferencesHandler;

public class TeamFitnessMetrics extends TeamHandler {

	TeamHandler tm = new TeamHandler();
	ProjectHandler pm = new ProjectHandler();
	PreferencesHandler prem = new PreferencesHandler();

	public ArrayList<HashMap<String, Double>> calAvgSkillCompetence() throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		ArrayList<HashMap<String, Double>> teamsComptence = new ArrayList<>();
		tm.loadFromFile();
		Map<String, Team> team = TeamHandler.getTeams();

		for (Map.Entry<String, Team> entry : team.entrySet()) {

			double avgSkillComp = calAverageShort(entry.getValue().getMember1(), entry.getValue().getMember2(),
					entry.getValue().getMember3(), entry.getValue().getMember4(), entry.getValue().getProjectID(),
					"AvgComp");

			HashMap<String, Double> map = new HashMap<>();

			System.out.println(entry.getValue().getTeamID() + " Your score: " + avgSkillComp);

			map.put(entry.getValue().getTeamID(), avgSkillComp);
			teamsComptence.add(map);
		}

		return teamsComptence;
	}

	private double calAverageShort(String member1, String member2, String member3, String member4, String projectID,
			String Identifier) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IOException {

		String std1 = super.checkIfLineExists("students.txt", member1);
		String std2 = super.checkIfLineExists("students.txt", member2);
		String std3 = super.checkIfLineExists("students.txt", member3);
		String std4 = super.checkIfLineExists("students.txt", member4);

		int[] skillArr = new int[4];

		if (std1 != null && std2 != null && std3 != null && std4 != null) {

			String s[] = new String[4];
			s[0] = std1;
			s[1] = std2;
			s[2] = std3;
			s[3] = std4;

			for (String loops : s) {

				String x[] = loops.split(" ");
				Student stud = new Student(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8]);

				skillArr[0] += stud.getP();
				skillArr[1] += stud.getN();
				skillArr[2] += stud.getA();
				skillArr[3] += stud.getW();

				System.out.println(
						"P: " + skillArr[0] + " N: " + skillArr[1] + " A: " + skillArr[2] + " W: " + skillArr[3]);
			}

			// return value based on requirement
			if (Identifier.equals("AvgComp")) {

				// Average Score
				double averageScore = (skillArr[0] / 4) + (skillArr[1] / 4) + (skillArr[2] / 4) + (skillArr[3] / 4);

				// Average Score standard deviation
				double mean = averageScore / 4;
				double standardDeviation = Math.sqrt((Math.pow(skillArr[0] - mean, 2) + Math.pow(skillArr[1] - mean, 2)
						+ Math.pow(skillArr[2] - mean, 2) + Math.pow(skillArr[3] - mean, 2)) / 4);

				// standard Deviation
				return Math.round(standardDeviation * 100) / 100.00;

			} else if (Identifier.equals("ShortFall")) {

				Map<String, Double> sortedTeam = new LinkedHashMap<String, Double>();
				Map<String, Integer> proj = new LinkedHashMap<String, Integer>();

				// Average Score
				// Separated Average Scores
				sortedTeam.put("P", (double) (skillArr[0] / 4));
				sortedTeam.put("N", (double) (skillArr[1] / 4));
				sortedTeam.put("A", (double) (skillArr[2] / 4));
				sortedTeam.put("W", (double) (skillArr[3] / 4));

				pm.loadFromFile();
				Map<String, Project> project = ProjectHandler.getProjects();
				proj = project.get(projectID).getSortedByValueDesc();

				Double TeamShortFall = 0.0;

				// Comparing the skills and calculating Skill Gap
				for (Map.Entry<String, Integer> pro : proj.entrySet()) {
					String proSkill = pro.getKey();
					Double proValue = pro.getValue().doubleValue();

					Double teamValue = sortedTeam.get(proSkill);

					if (proValue >= teamValue) {
						TeamShortFall += (proValue - teamValue);
					}

				}

				return TeamShortFall;
			} else {
				return 0;
			}

		} else {
			return 0;
		}

	}

	public ArrayList<HashMap<String, Double>> calSkillShortfall() throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		ArrayList<HashMap<String, Double>> teamsShortFall = new ArrayList<>();
		tm.loadFromFile();
		Map<String, Team> team = TeamHandler.getTeams();

		for (Map.Entry<String, Team> entry : team.entrySet()) {

			double skillShortfall = calAverageShort(entry.getValue().getMember1(), entry.getValue().getMember2(),
					entry.getValue().getMember3(), entry.getValue().getMember4(), entry.getValue().getProjectID(),
					"ShortFall");

			HashMap<String, Double> map = new HashMap<>();

			System.out.println(entry.getValue().getTeamID() + " Your skill gap score: " + skillShortfall);

			map.put(entry.getValue().getTeamID(), skillShortfall);
			teamsShortFall.add(map);
		}

		return teamsShortFall;
	}

	public ArrayList<HashMap<String, Integer>> calPreferences() throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		ArrayList<HashMap<String, Integer>> Preferences12 = new ArrayList<>();
		tm.loadFromFile();
		Map<String, Team> team = TeamHandler.getTeams();

		for (Map.Entry<String, Team> entry : team.entrySet()) {

			int teamPref = getPreferences(entry.getValue().getMember1(), entry.getValue().getMember2(),
					entry.getValue().getMember3(), entry.getValue().getMember4(), entry.getValue().getProjectID());

			HashMap<String, Integer> map = new HashMap<>();

			System.out.println(entry.getValue().getTeamID() + " Your pref score: " + teamPref);
			
			map.put(entry.getValue().getTeamID(), teamPref);
			Preferences12.add(map);
		}

		return Preferences12;

	}

	private int getPreferences(String member1, String member2, String member3, String member4, String projectID)
			throws IOException {

		prem.loadFromFile();
		Map<String, StudProjPreferences> studPref = PreferencesHandler.getStudPref();
		int pref12Count = 0;

		// get member1 student preferences hashmap list and on that key the first key of
		// that hashmap (i.e. projectID)
		// For e.g. map.get(map.keySet().toArray()[0]); 
		// studPref.get(tmember).getPreflist().keySet().toArray()[0]

		// check if team project ID matches students 1st and 2nd

		String members[] = new String[4];
		members[0] = member1;
		members[1] = member2;
		members[2] = member3;
		members[3] = member4;

		for (String tmember : members) {

			if (studPref.get(tmember).getPreflist().keySet().toArray()[0]
					.equals(projectID)
					|| studPref.get(tmember).getPreflist().keySet().toArray()[1].equals(projectID)) {
				
				pref12Count++;
			}

		}

		return pref12Count;
	}

}
