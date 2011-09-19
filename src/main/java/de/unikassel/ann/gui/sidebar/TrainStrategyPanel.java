package de.unikassel.ann.gui.sidebar;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import de.unikassel.ann.controller.Settings;

public class TrainStrategyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JComboBox comboBoxAlgorithm;
	public JSpinner spinnerLernRate;
	public JSpinner spinnerMomentum;
	public AbstractButton rdbtnOnline;
	public JRadioButton rdbtnOffline;
	public final ButtonGroup buttonGroup = new ButtonGroup();
	
	public JPanel strategiePanel;
	public JComboBox comboBoxTypStrategien;
	public JCheckBox chckbxActivateStrategie;
	public JSpinner spinnerMaxIterations;
	public JSpinner spinnerMaxIterations2;

	/**
	 * Create the panel.
	 */
	public TrainStrategyPanel() {

		setBorder(new TitledBorder(null,Settings.i18n.getString("sidebar.trainingsStrategy") , TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setSize(400, 351);

		JLabel lblAlgorithm = new JLabel(Settings.i18n.getString("sidebar.trainingsStrategy.algorithm")); 

		JLabel lblLernrate = new JLabel(Settings.i18n.getString("sidebar.trainingsStrategy.lernRate"));

		JLabel lblMomentum = new JLabel(Settings.i18n.getString("sidebar.trainingsStretegy.momentum"));

		comboBoxAlgorithm = new JComboBox();
		comboBoxAlgorithm.setModel(new DefaultComboBoxModel(new String[] { "Backpropagation", "Test" }));

		spinnerLernRate = new JSpinner();
		spinnerMomentum = new JSpinner();

		JLabel lblTrainingsmodus = new JLabel(Settings.i18n.getString("sidebar.trainingsStrategy.trainingmodus"));//Settings.i18n.getString("sidebar.trainingsStrategy.trainingmodus")

		rdbtnOnline = new JRadioButton();
		rdbtnOnline.setText(Settings.i18n.getString("sidebar.trainingsStrategy.onlineRB"));
		buttonGroup.add(rdbtnOnline);
		rdbtnOnline.setSelected(true);

		rdbtnOffline = new JRadioButton("Offline");
//		rdbtnOffline.setName(Settings.i18n.getString("sidebar.trainingStrategy.offlineRB"));
		buttonGroup.add(rdbtnOffline);

		strategiePanel = new JPanel();
		CardLayout cardLayout = new CardLayout();
		// TODO: use cardLayout
		strategiePanel.setBorder(new TitledBorder(null, Settings.i18n.getString("sidebar.trainingsStrategy.strategy"), TitledBorder.LEADING,TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(strategiePanel, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
										.addGroup(
												groupLayout
														.createParallelGroup(Alignment.TRAILING, false)
														.addGroup(
																groupLayout.createSequentialGroup().addComponent(lblMomentum)
																		.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(spinnerMomentum, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout.createSequentialGroup().addComponent(lblLernrate)
																		.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(spinnerLernRate, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(lblAlgorithm)
																		.addGap(52)
																		.addComponent(comboBoxAlgorithm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout.createParallelGroup(Alignment.LEADING).addComponent(rdbtnOnline).addComponent(rdbtnOffline)))
										.addComponent(lblTrainingsmodus)).addContainerGap(20, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblAlgorithm)
										.addComponent(comboBoxAlgorithm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblLernrate)
										.addComponent(spinnerLernRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblMomentum)
										.addComponent(spinnerMomentum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblTrainingsmodus).addComponent(rdbtnOnline))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtnOffline).addGap(18)
						.addComponent(strategiePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JLabel lblTypStrategien = new JLabel(Settings.i18n.getString("sidebar.trainingsStrategy.strategy.type"));

		comboBoxTypStrategien = new JComboBox();
		comboBoxTypStrategien.setModel(new DefaultComboBoxModel(new String[] { "MaxIterations" }));

		chckbxActivateStrategie = new JCheckBox("");
		chckbxActivateStrategie.setSelected(true);

		JLabel lblMaxIterations = new JLabel(Settings.i18n.getString("sidebar.trainingsStrategy.strategy.maxIterations"));

		spinnerMaxIterations = new JSpinner();

		JLabel label = new JLabel(Settings.i18n.getString("sidebar.trainingsStrategy.strategy.maxIterations2"));

		spinnerMaxIterations2 = new JSpinner();
		GroupLayout gl_strategiePanel = new GroupLayout(strategiePanel);
		gl_strategiePanel.setHorizontalGroup(gl_strategiePanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_strategiePanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_strategiePanel
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_strategiePanel
														.createSequentialGroup()
														.addGroup(
																gl_strategiePanel.createParallelGroup(Alignment.LEADING).addComponent(lblTypStrategien)
																		.addComponent(lblMaxIterations))
														.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
														.addGroup(
																gl_strategiePanel
																		.createParallelGroup(Alignment.TRAILING)
																		.addComponent(comboBoxTypStrategien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(spinnerMaxIterations, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
										.addGroup(
												gl_strategiePanel.createSequentialGroup()
														.addComponent(label, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
														.addComponent(spinnerMaxIterations2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxActivateStrategie).addGap(21)));
		gl_strategiePanel.setVerticalGroup(gl_strategiePanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_strategiePanel
						.createSequentialGroup()
						.addGroup(
								gl_strategiePanel.createParallelGroup(Alignment.BASELINE).addComponent(lblTypStrategien)
										.addComponent(chckbxActivateStrategie)
										.addComponent(comboBoxTypStrategien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(
								gl_strategiePanel.createParallelGroup(Alignment.BASELINE).addComponent(lblMaxIterations)
										.addComponent(spinnerMaxIterations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_strategiePanel.createParallelGroup(Alignment.BASELINE).addComponent(label)
										.addComponent(spinnerMaxIterations2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))));
		strategiePanel.setLayout(gl_strategiePanel);
		setLayout(groupLayout);
	}
}
