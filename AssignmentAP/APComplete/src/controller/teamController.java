package controller;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Team;
import model.TeamFitnessMetrics;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import handler.*;

public class teamController implements Initializable {

	TeamHandler tmhand = new TeamHandler();
	TeamFitnessMetrics tfm = new TeamFitnessMetrics();

	@FXML
	TextField t11, t12, t13, t14, t21, t22, t23, t24, t31, t32, t33, t34, t41, t42, t43, t44, t51, t52, t53, t54,
			idField;
	@FXML
	Button addBtn, swapBtn;
	@FXML
	VBox mainVBox;
	private String value1, value2;
	private boolean value1Set = false;
	private boolean value2Set = false;
	private CheckBox checkBox1, checkBox2;
	@FXML
	BarChart compGraph, skillGraph, prefGraph;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			initializeTeams();

			// keep file cleaned for writing
			new TeamHandler().writeCleanedFile();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | IOException e) {

			e.printStackTrace();
		}

		addBtn.setOnAction(event -> {
			String stId = idField.getText();
			String oldLine = tmhand.findUnfilledTeam();

			if (oldLine != null) {
				String newLine = oldLine + " " + stId;
				tmhand.appendReplace("selections.txt", oldLine, newLine);
			}

			Pattern p = Pattern.compile("^s|S[0-9]+");
			Matcher m = p.matcher(stId);

			if (m.find()) {

				if (!value1.equals(stId.toUpperCase())) {

					String team1 = checkBox1.getId().toUpperCase().substring(1, 3);
					String line1 = tmhand.checkIfLineExists("selections.txt", team1);
					String present = tmhand.checkIfValueExists("selections.txt", stId);

					if (present == null) {
						String newL1 = line1.replace(value1, stId.toUpperCase());
						tmhand.appendReplace("selections.txt", line1, newL1);
					} else {
						JOptionPane.showMessageDialog(null, "Student already exists");
					}
				}
			}

			idField.setText("");
			try {
				try {
					initializeTeams();
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		swapBtn.setOnAction(event -> {
			System.out.println("SWAPPING: " + value1 + " W " + value2);
			value1Set = false;
			value2Set = false;
			checkBox1.setSelected(false);
			checkBox2.setSelected(false);
			String team1 = checkBox1.getId().toUpperCase().substring(1, 3);
			String team2 = checkBox2.getId().toUpperCase().substring(1, 3);

			String line1 = tmhand.checkIfLineExists("selections.txt", team1);
			String newLine1 = line1.replace(value1, value2);

			String line2 = tmhand.checkIfLineExists("selections.txt", team2);
			String newLine2 = line2.replace(value2, value1);

			tmhand.appendReplace("selections.txt", line1, newLine1);
			tmhand.appendReplace("selections.txt", line2, newLine2);
			try {
				initializeTeams();
			} catch (IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		});

	}

	@FXML
	void checkBoxClicked(Event event) {
		Scene scene = ((Node) event.getSource()).getScene();
		CheckBox checkBox = (CheckBox) event.getTarget();
		String id = checkBox.getId().substring(1);
		TextField textField;

		if (value2Set) {
			checkBox.setSelected(false);
			JOptionPane.showMessageDialog(null, "You have already selected 2 IDs");
		} else {
			if (!value1Set) {
				textField = (TextField) scene.lookup("#" + id);
				value1 = textField.getText();
				value1Set = true;
				checkBox1 = checkBox;

			} else {
				textField = (TextField) scene.lookup("#" + id);
				value2 = textField.getText();
				value1Set = false;
				value2Set = true;
				checkBox2 = checkBox;

			}
		}

	}

	private void initializeTeams() throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		tmhand.loadFromFile();
		Map<String, Team> team = TeamHandler.getTeams();
		for (Map.Entry<String, Team> entry : team.entrySet()) {

			loadTeams(entry.getValue().getTeamID(), entry.getValue().getMember1(), entry.getValue().getMember2(),
					entry.getValue().getMember3(), entry.getValue().getMember4());
		}

		displayCompetencyGraph();
		displayPreferencesGraph();
		displaySkillsGraph();

	}

	private void displayPreferencesGraph() throws IOException, IllegalAccessException, IllegalArgumentException,
	InvocationTargetException, NoSuchMethodException, SecurityException {
		
		ArrayList<HashMap<String, Integer>> teamPreferences = tfm.calPreferences();
		prefGraph.getData().clear();
		
		// Defining the axes
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setCategories(FXCollections
				.<String>observableArrayList(Arrays.asList("Team 1", "Team 2", "Team 3", "Team 4", "team 5")));
		xAxis.setLabel("category");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("score");
		
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		series2.setName("Teams");
		
		for (int i = 0; i < teamPreferences.size(); i++) {
			HashMap map2 = teamPreferences.get(i);
			Double t2 = Double.parseDouble(map2.get("T" + (i + 1)).toString());
			series2.getData().add(new XYChart.Data<>("Team" + (i + 1), t2));
		}
		
		prefGraph.getData().addAll(series2);
	}

	public void displayCompetencyGraph() throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		ArrayList<HashMap<String, Double>> teamsComptence = tfm.calAvgSkillCompetence();
		compGraph.getData().clear();
		
		// Defining the axes
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setCategories(FXCollections
				.<String>observableArrayList(Arrays.asList("Team 1", "Team 2", "Team 3", "Team 4", "team 5")));
		xAxis.setLabel("category");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("score");

		// Prepare XYChart.Series objects by setting data
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Teams");

		for (int i = 0; i < teamsComptence.size(); i++) {
			HashMap map1 = teamsComptence.get(i);
			Double t1 = Double.parseDouble(map1.get("T" + (i + 1)).toString());
			series1.getData().add(new XYChart.Data<>("Team" + (i + 1), t1));
		}
		
		// Setting the data to bar chart
		compGraph.getData().addAll(series1);
	}

	public void displaySkillsGraph() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {

		skillGraph.getData().clear();

		// Defining the axes
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setCategories(FXCollections
				.<String>observableArrayList(Arrays.asList("Team 1", "Team 2", "Team 3", "Team 4", "team 5")));
		xAxis.setLabel("category");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("score");

		// Prepare XYChart.Series objects by setting data
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Teams");

		ArrayList<HashMap<String, Double>> teamsShortFall = tfm.calSkillShortfall();
		for (int i = 0; i < teamsShortFall.size(); i++) {
			HashMap map = teamsShortFall.get(i);
			Double t1 = Double.parseDouble(map.get("T" + (i + 1)).toString());
			series1.getData().add(new XYChart.Data<>("Team" + (i + 1), t1));
		}

		// Setting the data to bar chart
		skillGraph.getData().addAll(series1);

	}

	void loadTeams(String TeamId, String member1, String member2, String member3, String member4) {

		if (TeamId.equals("T1")) {
			t11.setText(member1);
			t12.setText(member2);
			t13.setText(member3);
			t14.setText(member4);
		} else if (TeamId.equals("T2")) {
			t21.setText(member1);
			t22.setText(member2);
			t23.setText(member3);
			t24.setText(member4);
		} else if (TeamId.equals("T3")) {
			t31.setText(member1);
			t32.setText(member2);
			t33.setText(member3);
			t34.setText(member4);
		} else if (TeamId.equals("T4")) {
			t41.setText(member1);
			t42.setText(member2);
			t43.setText(member3);
			t44.setText(member4);
		} else if (TeamId.equals("T5")) {
			t51.setText(member1);
			t52.setText(member2);
			t53.setText(member3);
			t54.setText(member4);
		}

	}
}
