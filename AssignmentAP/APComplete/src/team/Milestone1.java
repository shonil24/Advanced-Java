package team;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import model.*;

public class Milestone1 {

	public static void main(String[] args) throws IOException {

		// Create a Scanner object for input
		Scanner in = new Scanner(System.in);

		Map<String, Company> mc = new HashMap<String, Company>();
		Map<String, Owner> po = new HashMap<String, Owner>();
		Map<String, Project> pr = new LinkedHashMap<String, Project>();
		Map<String, Student> smap = new LinkedHashMap<String, Student>();
		Map<String, Integer> mskill = new HashMap<String, Integer>();
		Map<String, StudProjPreferences> spmap = new LinkedHashMap<String, StudProjPreferences>();
		Map<String, Integer> preflist = new LinkedHashMap<String, Integer>();
		Map<String, Integer> projpref = new LinkedHashMap<String, Integer>();
		Map<String, Team> team = new LinkedHashMap<String, Team>();

		// Menu section
		// Flag used to quit from menu
		boolean quit = false;
		int choice = 0, c1 = 0;

		do {

			System.out.println("               MENU               ");
			System.out.println("----------------------------------");
			System.out.println("1. Add Company");
			System.out.println("2. Add Project Owner");
			System.out.println("3. Add Project");
			System.out.println("4. Capture Student Personalities");
			System.out.println("5. Add Student Preferences");
			System.out.println("6. Shortlist Projects");
			System.out.println("7. Form Teams");
			System.out.println("8. Team Fitness Metrics");
			System.out.println("9. Quit");

			// Loop until valid choice entered
			while (true) {

				// User choice in number
				System.out.println("\n Select a valid Menu option:");

				try {
					choice = in.nextInt();
					c1 = 1;
				} catch (InputMismatchException e) {
					System.out.println("Please enter number only");
				}

				if (c1 == 1) {
					break;
				}
			}

			// Switch case to execute statement sequence for each menu block
			switch (choice) {
			case 1:

				// Company Details
				System.out.println("Enter the total no of entries for company details:");
				int nCom = in.nextInt();

				for (int i = 0; i < nCom; i++) {

					Company com = new Company();

					// Company ID Start. For accepting random unique values
					checkCompanyDuplicates("company ID", in, mc, "c", com);
					// Company ID End.

					// ABN Start
					checkCompanyDuplicates("ABN Number", in, mc, "", com);
					// ABN End

					System.out.println("Enter the company name:");
					com.setCompanyName(in.next());

					System.out.println("Enter the company URL:");
					com.setCompanyURL(in.next());

					// To consume the \n character
					in.nextLine();

					System.out.println("Enter the company Address:");
					com.setCompanyAddress(in.nextLine());

					mc.put(com.getCompanyID(), com);
				}

				// create new BufferedWriter for the output file
				BufferedWriter bf = new BufferedWriter(new FileWriter("companies.txt"));

				try {

					// bf.write("CompanyID " + "ABN Number " + "Company Name " + "Company URL\t" +
					// "Company Address");
					// bf.newLine();

					// iterate map entries
					for (Map.Entry<String, Company> entry : mc.entrySet()) {

						// put key and value separated by a colon
						bf.write(entry.getValue().getCompanyID() + "   " + entry.getValue().getABN_Number() + "\t    "
								+ entry.getValue().getCompanyName() + "\t  " + entry.getValue().getCompanyURL() + "\t"
								+ entry.getValue().getCompanyAddress());

						// new line
						bf.newLine();
					}

					bf.flush();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {

					try {
						// always close the writer
						bf.close();
					} catch (Exception e) {
					}
				}

				break;
			case 2:

				// Project Owner Details
				System.out.println("Enter the total no of entries for owner details:");
				int nOwn = in.nextInt();

				for (int i = 0; i < nOwn; i++) {

					Owner owner = new Owner();

					System.out.println("Enter the project owner first name:");
					owner.setFirstName(in.next());

					System.out.println("Enter the project owner surname:");
					owner.setSurName(in.next());

					// Owner ID Start. For accepting random unique values
					checkOwnerDuplicates("owner ID", in, po, "own", owner);
					// Owner ID End.

					in.nextLine();

					System.out.println("Enter the role for e.g software engineer:");
					owner.setRole(in.nextLine());

					System.out.println("Enter the email id of project owner:");
					owner.setOemail(in.next());

					// Owner-company ID Start. For accepting random unique values
					while (true) {

						System.out.println("Enter the company ID project owner is associated to:");
						String oCID = in.next();

						if (mc.containsKey("c" + oCID)) {
							owner.setOcompanyID("c" + oCID);
							break;
						} else {
							System.out.println("Please try again!! Project owner entered company ID doesn't exist");
						}
					}
					// Owner-company ID End.
					po.put(owner.getOwnerID(), owner);
				}

				// create new BufferedWriter for the output file
				BufferedWriter bfo = new BufferedWriter(new FileWriter("owners.txt"));

				try {

					// iterate map entries
					for (Map.Entry<String, Owner> entry : po.entrySet()) {

						// put key and value separated by a colon
						bfo.write(entry.getValue().getOwnerID() + "   " + entry.getValue().getFirstName() + "\t"
								+ entry.getValue().getSurName() + "\t\t" + entry.getValue().getRole() + "\t"
								+ entry.getValue().getOemail() + "\t\t" + entry.getValue().getOcompanyID());

						// new line
						bfo.newLine();
					}

					bfo.flush();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {

					try {
						// always close the writer
						bfo.close();
					} catch (Exception e) {
					}
				}

				break;
			case 3:

				// Project Details
				System.out.println("Enter the total no of entries for project details:");
				int nProj = in.nextInt();

				for (int i = 0; i < nProj; i++) {

					Project proj = new Project();

					in.nextLine();

					System.out.println("Enter the project title:");
					proj.setTitle(in.nextLine());

					// Project ID Start. For accepting random unique values
					checkProjectDuplicates("project ID", in, pr, "pr", proj);
					// Project ID End.

					in.nextLine();

					System.out.println(
							"Enter the description for e.g <your project title> will be developed by a team of <your team member count> using <your methodology for the project>:");
					proj.setDescription(in.nextLine());

					// Owner-proj ID Start. For accepting random unique values
					while (true) {

						System.out.println("Enter the project owner ID:");
						String pOID = in.next();

						if (po.containsKey("own" + pOID)) {
							proj.setPownerID("own" + pOID);
							break;
						} else {
							System.out.println("Please try again!! Project owner ID doesn't exist");
						}
					}
					// Owner-proj ID End.

					System.out.println("Technical skill categories for project");
					System.out.println(
							"(P) Programming and Software Engineering \n(N) Networking and Security \n(A) Analytics and Big Data \n(W) Web & Mobile applications");
					System.out.println("Enter any number between 1-4 to rank each skill without repeating");

					// All skills will be added into hashmap list dynamically with validation
					System.out.println("Enter ranking for P skill");
					setValidSkill("P", in, mskill);

					System.out.println("Enter ranking for N skill");
					setValidSkill("N", in, mskill);

					System.out.println("Enter ranking for A skill");
					setValidSkill("A", in, mskill);

					System.out.println("Enter ranking for W skill");
					setValidSkill("W", in, mskill);

					// We are sorting the ranking of skills in descending order and wrapping into
					// Project object
					proj.setSortedByValueDesc(mskill.entrySet().stream()
							.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toMap(
									Map.Entry::getKey, Map.Entry::getValue, (p1, p2) -> p1, LinkedHashMap::new)));

					pr.put(proj.getProjectID(), proj);

					// clear unsorted list hashmap
					mskill.clear();
				}

				// create new BufferedWriter for the output file
				BufferedWriter bfp = new BufferedWriter(new FileWriter("projects.txt"));

				try {

					// iterate map entries
					for (Map.Entry<String, Project> entry : pr.entrySet()) {

						// put key and value separated by a colon
						bfp.write(entry.getValue().getProjectID() + "\t" + entry.getValue().getTitle() + "\t"
								+ entry.getValue().getDescription() + "\t" + entry.getValue().getPownerID() + "\t"
								+ entry.getValue().getSortedByValueDesc());
						bfp.newLine();
					}

					bfp.flush();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {

					try {
						// always close the writer
						bfp.close();
					} catch (Exception e) {
					}
				}

				break;
			case 4:
				// Student details

				Stream<String> rows = Files.lines(Paths.get("students.txt"));

				rows.map(x -> x.split(" ")).forEachOrdered(
						x -> {
							try {
								smap.put(x[0], new Student(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8]));
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
									| NoSuchMethodException | SecurityException e1) {
								
								e1.printStackTrace();
							}
						});

				Map<String, Student> studinfo = new LinkedHashMap<String, Student>();
				int a = 1, b = 1, c = 1, d = 1;

				for (Map.Entry<String, Student> entry : smap.entrySet()) {

					Student stud = new Student();

					// set and sort already read skills of a file (hashmap) for another student info
					// file
					stud.setSskill(entry.getValue().getSskill().entrySet().stream()
							.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toMap(
									Map.Entry::getKey, Map.Entry::getValue, (p1, p2) -> p1, LinkedHashMap::new)));

					// Persona start. Loop until one of the persona letters
					while (true) {
						System.out.println("Enter the Personality type for " + entry.getValue().getStudentID()
								+ " for e.g A/B/C/D:");
						String persona = in.next();

						if (persona.equalsIgnoreCase("A") && a <= 5) {
							stud.setPersonality(persona.toUpperCase());
							a++;
							break;
						} else if (persona.equalsIgnoreCase("B") && b <= 5) {
							stud.setPersonality(persona.toUpperCase());
							b++;
							break;
						} else if (persona.equalsIgnoreCase("C") && c <= 5) {
							stud.setPersonality(persona.toUpperCase());
							c++;
							break;
						} else if (persona.equalsIgnoreCase("D") && d <= 5) {
							stud.setPersonality(persona.toUpperCase());
							d++;
							break;
						} else {
							System.out.println("Imbalanced personality types OR Incorrect personality type!!");
						}
					}

					System.out.println("Enter the 2 student conflicts for (only numbers) "
							+ entry.getValue().getStudentID() + ":");

					try {
						stud.setConflict_1("s" + in.nextInt());
						stud.setConflict_2("s" + in.nextInt());
					} catch (InputMismatchException e) {

						// for no conflicts set s
						stud.setConflict_1("s");
						stud.setConflict_2("s");
					}

					studinfo.put(entry.getKey(), stud);
				}

				// create new BufferedWriter for the output file
				BufferedWriter bfsi = new BufferedWriter(new FileWriter("studentinfo.txt"));

				try {

					// iterate map entries
					for (Map.Entry<String, Student> entry : studinfo.entrySet()) {

						// put key and value separated by a colon
						bfsi.write(entry.getKey() + "  " + entry.getValue().getSskill() + " "
								+ entry.getValue().getPersonality() + " " + entry.getValue().getConflict_1() + " "
								+ entry.getValue().getConflict_2());
						bfsi.newLine();

					}

					bfsi.flush();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {

					try {
						// always close the writer
						bfsi.close();
					} catch (Exception e) {
					}
				}

				break;
			case 5:
				// project preferences

				Stream<String> prows = Files.lines(Paths.get("projects.txt"));

				// only list the project IDs. Dont store the details for now
				System.out.println("List of projects IDs");
				prows.map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").trim().split("\\s+")).forEachOrdered(x -> {
					System.out.println(x[0]);
					pr.put(x[0], new Project(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10], x[11]));
				});

				Stream<String> srows = Files.lines(Paths.get("students.txt"));

				srows.map(x -> x.split(" ")).forEachOrdered(
						x -> {
							try {
								smap.put(x[0], new Student(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8]));
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
									| NoSuchMethodException | SecurityException e1) {
								
								e1.printStackTrace();
							}
						});

				Set<String> pvisited = new HashSet<>();

				// create new BufferedWriter for the output file
				BufferedWriter bpre = new BufferedWriter(new FileWriter("preferences.txt"));

				for (Map.Entry<String, Student> entry : smap.entrySet()) {

					StudProjPreferences spp = new StudProjPreferences();

					for (int i = 4; i > 0; i--) {

						while (true) {

							// (4 indicates top rank) and (1 indicates low rank)
							System.out.println(
									entry.getValue().getStudentID() + " please enter your projectID for rank 4-1:");
							String pro = in.next().toLowerCase();

							if (pr.containsKey(pro)) {

								// For no repeated projects
								if (pvisited.add(pro)) {
									preflist.put(pro, i);
									break;
								} else {
									System.out.println("Proj pref already added by you. Please add another proj");
								}

							} else {
								System.out.println("Invalid!! project ID");
							}

						}

					}

					// clear for next student
					pvisited.clear();

					spp.setPreflist(preflist);
					spmap.put(entry.getValue().getStudentID(), spp);

					// clear preflist for next student.
					// Needs another instance for another student. For same instance it remains
					// cleared.
					preflist = new LinkedHashMap<String, Integer>();

				}

				try {

					// iterate map entries
					for (Map.Entry<String, StudProjPreferences> entry : spmap.entrySet()) {

						// put key and value separated by a colon
						bpre.write(entry.getKey() + "  " + entry.getValue().getPreflist());
						bpre.newLine();

					}

					bpre.flush();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {

					try {
						// always close the writer
						bpre.close();
					} catch (Exception e) {
					}
				}

				break;
			case 6:

				Stream<String> prerows = Files.lines(Paths.get("preferences.txt"));
				List<String> list = prerows.collect(Collectors.toList());

				// size from project files for no of projects
				// Please use this at first. Stream works once for one file
				Stream<String> projrows = Files.lines(Paths.get("projects.txt"));
				List<String> projlist = projrows.collect(Collectors.toList());
				projrows.close();

				for (int i = 1; i <= projlist.size(); i++) {

					// returned sum of that proj added
					projpref.put("pr" + i, getPrefSum(i, list));
				}

				// arranging to higher ranked projects
				projpref = projpref.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (p1, p2) -> p1,
								LinkedHashMap::new));

				//////

				String spline = null;
				List<String> spprint = new ArrayList<String>();

				int count = 5, flag = 0;

				Iterator<String> iter = projlist.iterator();

				while (iter.hasNext()) {

					spline = iter.next();
					for (Map.Entry<String, Integer> entry : projpref.entrySet()) {

						// loop for 5 times only
						if (count >= 1) {

							// That line comes in one of the top list
							if (spline.startsWith(entry.getKey())) {
								flag = 1;
							}

						} else {

							// out after 5th time
							break;
						}

						// add matching lines only
						if (flag == 1) {
							spprint.add(spline);
							break;
						}
						count--;
					}
					// reset flag
					flag = 0;

					// set count again
					count = 5;
				}

				// Declare only after file manipulation. This will overwrite
				BufferedWriter bspout = new BufferedWriter(new FileWriter("projects.txt"));

				// {spot} weird error. Without try-catch File Manipulation doesnt work
				try {
					for (String s : spprint) {

						bspout.write(s);
						bspout.newLine();
					}
					bspout.flush();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {

					try {
						// always close the writer
						bspout.close();
					} catch (Exception e) {
					}
				}

				break;
			case 7:

				String projectID, teamID;
				Set<String> tempMembers;

				// repeat
				Stream<String> studrows = Files.lines(Paths.get("studentinfo.txt"));
				studrows.map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").trim().split("\\s+")).forEachOrdered(x -> {
					try {
						smap.put(x[0],
								new Student(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10], x[11]));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException e) {
						
						e.printStackTrace();
					}
				});

				Stream<String> prorows = Files.lines(Paths.get("projects.txt"));

				prorows.map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").trim().split("\\s+")).forEachOrdered(x -> {
					pr.put(x[0], new Project(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10], x[11]));
				});
				// repeat

				Stream<String> trows = Files.lines(Paths.get("selections.txt"));
				List<String> tlist = trows.collect(Collectors.toList());

				StringBuilder allMembers = new StringBuilder(100);
				
				// {SPOT} Unnecessary error. Output is displaying
				try {
					tlist.stream().map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").split("\\s+")).forEachOrdered(x -> {
						allMembers.append(x[2])
						.append(x[3])
			          	.append(x[4])
			          	.append(x[5]).toString();
						//team.put(x[0], new Team(x[0], x[1], x[2], x[3], x[4], x[5]));
					});
				} catch (IndexOutOfBoundsException e) {
					
				}

				if (!smap.isEmpty()) {

					System.out.print("list of existing projects:-");
					System.out.println(pr.keySet());

					System.out.println("Enter the Project ID to form Team:");
					projectID = in.next();

					if (pr.containsKey(projectID)) {

						tempMembers = selectTeamMembers(smap, in, allMembers);

						if (!tempMembers.equals(null)) {
							teamID = "T" + (team.size() + 1);
							team.put(teamID, new Team(projectID, teamID, tempMembers));
						}

					} else {
						System.out.println("Entered Project ID not found");
					}
				}

				// Declare only after file manipulation. This will overwrite
				BufferedWriter btout = new BufferedWriter(new FileWriter("selections.txt", true));

				try {
					// iterate map entries
					for (Map.Entry<String, Team> entry : team.entrySet()) {

						// put key and value separated by a colon
						btout.append(entry.getValue().getTeamID() + " " + entry.getValue().getProjectID() + " "
								+ entry.getValue().getTempMembers());
						btout.newLine();

					}
					btout.flush();

				} catch (IOException e) {
					e.printStackTrace();
				} finally {

					try {
						// always close the writer
						btout.close();
					} catch (Exception e) {
					}
				}
				break;
			case 8:
				// Fitness metrics
				
				
				break;
			case 9:

				// quit is set to true which the program flow will then exit from the menu
				quit = true;
				break;
			}

			// continue displaying the menu until the user enters choice for exit
		} while (quit == false);

		// Closing scanner object as there is no input to read
		in.close();
	}

	public static Set<String> selectTeamMembers(Map<String, Student> smap, Scanner in, StringBuilder allMembers) {
		boolean perFlg = false;
		String studentID;
		Set<String> teamGroup = new HashSet<String>();

		while (teamGroup.size() != 4) {
			System.out.println("Enter the Student ID for team member: ");
			studentID = in.next();

			if (smap.containsKey(studentID)) {

				if (!allMembers.toString().contains(studentID)) {

					if (teamGroup.size() > 0) {

						if (confictCheck(teamGroup, studentID, smap)) {
							System.out.println("StudentConflictException");
						} else if (perFlg == true && smap.get(studentID).getPersonality().equalsIgnoreCase("A")) {
							System.out.println("LeaderRepeatedException");
						} else if (perFlg == false && teamGroup.size() >= 3) {
							System.out.println("NoLeaderException");
						} else {

							// Student added as team member
							if (!teamGroup.contains(studentID)) {

								int imCount = 0;
								for (String im : teamGroup) {

									if (smap.get(im).getPersonality().equals(smap.get(studentID).getPersonality())) {

										// Should be 1 only
										imCount++;
									}

								}

								if (imCount <= 1) {

									// add now all checks done
									teamGroup.add(studentID);

									// check for A leader
								} else {
									System.out.println("ImbalanceException");
								}
							} else {
								System.out.println("RepeatedMemberException");
							}
						}

					} else {

						if (teamGroup.size() == 0) {
							// add student in a team with 0 members
							teamGroup.add(studentID);
						}

					}

				} else {
					System.out.println("InvalidMemberException");
				}

			} else {
				System.out.println("Entered Student ID not found");
			}

			// If A Personality leader is added to team set the flag
			if (smap.get(studentID).getPersonality().equalsIgnoreCase("A")) {
				perFlg = true;
			}

		}

		return teamGroup;

	}

	private static boolean confictCheck(Set<String> teamGroup, String studentID, Map<String, Student> smap) {

		boolean conFlg = false;

		for (String memberID : teamGroup) {

			if (studentID.equals(smap.get(memberID).getConflict_1())
					|| studentID.equals(smap.get(memberID).getConflict_2())) {
				System.out.println(studentID + " has a confict with " + memberID);
				conFlg = true;
			}

		}

		return conFlg;
	}

	private static int getPrefSum(int i, List<String> list) throws IOException {

		int sum[] = new int[4];

		list.stream().map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").trim().split("\\s+")).forEachOrdered(x -> {

			if (("pr" + i).equals(x[1])) {
				sum[0] += 4;
			} else if (("pr" + i).equals(x[3])) {
				sum[1] += 3;
			} else if (("pr" + i).equals(x[5])) {
				sum[2] += 2;
			} else if (("pr" + i).equals(x[7])) {
				sum[3] += 1;
			}

		});

		int sumtotal = 0;
		for (int s = 0; s < 4; s++) {
			sumtotal += sum[s];
		}

		return sumtotal;

	}

	private static void checkProjectDuplicates(String string, Scanner in, Map<String, Project> pr, String prefix,
			Project proj) {

		String uProjectID = null;

		while (true) {

			System.out.println("Enter the unique " + string + " number:");
			uProjectID = in.next();

			if (!pr.containsKey(prefix + uProjectID)) {

				if (string.equals("project ID")) {
					proj.setProjectID(prefix + uProjectID);
					break;
				}
				break;
			} else {
				System.out.println(string + " already exists");
			}
		}
	}

	private static void checkOwnerDuplicates(String string, Scanner in, Map<String, Owner> po, String prefix,
			Owner owner) {

		String uOwnerID = null;

		while (true) {

			System.out.println("Enter the unique " + string + " number:");
			uOwnerID = in.next();

			if (!po.containsKey(prefix + uOwnerID)) {

				if (string.equals("owner ID")) {
					owner.setOwnerID(prefix + uOwnerID);
					break;
				}
				break;
			} else {
				System.out.println(string + " already exists");
			}
		}
	}

	private static void checkCompanyDuplicates(String string, Scanner in, Map<String, Company> mc, String prefix,
			Company com) {

		String uCompanyID = null;

		while (true) {

			System.out.println("Enter the unique " + string + " number:");
			uCompanyID = in.next();

			if (!mc.containsKey(prefix + uCompanyID)) {

				if (string.equals("company ID")) {
					com.setCompanyID(prefix + uCompanyID);
					break;
				} else if (string.equals("ABN Number")) {
					com.setABN_Number(prefix + uCompanyID);
					break;
				}
			} else {
				System.out.println(string + " already exists");
			}
		}
	}

	public static void setValidSkill(String prefix, Scanner in, Map<String, Integer> mskill) {

		int universal = 0, flag = 0;

		while (true) {

			try {
				universal = in.nextInt();
				flag = 1;
			} catch (InputMismatchException e) {
				System.out.println("Please enter number only");
			}

			if (flag == 1) {

				if (!mskill.containsValue(universal) && universal >= 0 && universal <= 4) {
					mskill.put(prefix, universal);
					break;
				} else {
					System.out.println("Rank number already exists. Please try again");
				}
			}
		}
	}
}
