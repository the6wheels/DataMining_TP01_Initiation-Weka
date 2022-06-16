package weka.api;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;


import javax.swing.JFileChooser;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

public class apigui {
	
	
	private JFileChooser filee;

	private JFrame frame;
	private File file;
	private File file2;
	private JRadioButton A1;
	private JRadioButton A2;
	private JRadioButton A3;
	private JRadioButton A4;
	private JRadioButton B1;
	private JRadioButton B2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					apigui window = new apigui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public apigui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		

		frame = new JFrame();
		frame.setBounds(100, 100, 881, 572);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 132, 380, 44);
		frame.getContentPane().add(label);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 188, 826, 341);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
//		Button for opening the  learning dataset
		
		JButton btnNewButton = new JButton("Load learn set");
		btnNewButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setFileFilter(new FileNameExtensionFilter("ARFF File", "arff"));
	            int option = fileChooser.showOpenDialog(frame);
	            if(option == JFileChooser.APPROVE_OPTION){
	            	   file = fileChooser.getSelectedFile();
		               label.setText("File Selected: " + file.getName());    
	            }else{
	               label.setText("Loading ARFF file canceled");
	            }
	     
	            
	         }
	      });
		
//		Button for opening the  learning testset
		
		JButton btnLoadTestSet = new JButton("Load test set");
		btnLoadTestSet.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            JFileChooser fileChooser2 = new JFileChooser();
	            fileChooser2.setFileFilter(new FileNameExtensionFilter("ARFF File", "arff"));
	            int option = fileChooser2.showOpenDialog(frame);
	            if(option == JFileChooser.APPROVE_OPTION){
		               file2 = fileChooser2.getSelectedFile();
		               label.setText("File Selected: " + file2.getName());   			
	            }else{
	               label.setText("Loading TEST file canceled");
	            }
	            
	         }
	      });
		btnLoadTestSet.setBounds(177, 20, 144, 25);
		frame.getContentPane().add(btnLoadTestSet);

		
		
		
		btnNewButton.setBounds(12, 20, 144, 25);
		frame.getContentPane().add(btnNewButton);
		
//		Button for view the  learning learn set
		
		JButton btnViewArff = new JButton("View learn file");
		btnViewArff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//textArea.setText("");
				try {
			          BufferedReader input = new BufferedReader(new InputStreamReader(
			              new FileInputStream(file)));
			          textArea.read(input, "READING FILE :-)");
			        } catch (Exception e) {
			          e.printStackTrace();
			        }
				
			}
		});
		btnViewArff.setBounds(12, 57, 144, 25);
		frame.getContentPane().add(btnViewArff);
		
		
		
		JRadioButton A1 = new JRadioButton("C.4.5");
		A1.setBounds(457, 43, 200, 23);
		frame.getContentPane().add(A1);
		
		JRadioButton A2 = new JRadioButton("RandomForset");
		A2.setBounds(457, 80, 200, 23);
		frame.getContentPane().add(A2);
		
		JLabel lblChooseValidationOption = new JLabel("Choose Classifier Algorithm :");
		lblChooseValidationOption.setBounds(328, 20, 216, 15);
		frame.getContentPane().add(lblChooseValidationOption);
		
		JLabel lblChooseValidationOption_1 = new JLabel("Choose Validation Option :");
		lblChooseValidationOption_1.setBounds(620, 20, 200, 15);
		frame.getContentPane().add(lblChooseValidationOption_1);
		
		JRadioButton B1 = new JRadioButton("Cross Validation");
		B1.setBounds(671, 43, 200, 23);
		frame.getContentPane().add(B1);
		
		JRadioButton B2 = new JRadioButton("Use test set file");
		B2.setBounds(671, 80, 200, 23);
		frame.getContentPane().add(B2);
		
		JRadioButton A4 = new JRadioButton("One Rule");
		A4.setBounds(457, 157, 200, 23);
		frame.getContentPane().add(A4);
		
		JRadioButton A3 = new JRadioButton("Naive Bayes");
		A3.setBounds(457, 120, 200, 23);
		frame.getContentPane().add(A3);
		
		ButtonGroup gpp = new ButtonGroup();
		gpp.add(A1);
		gpp.add(A2);
		gpp.add(A3);
		gpp.add(A4);
		
		ButtonGroup gpp2 = new ButtonGroup();
		gpp2.add(B1);
		gpp2.add(B2);
		
		
//		Button for clearing screen
		
		JButton btnClearResults = new JButton("Clear Results");
		btnClearResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		btnClearResults.setBounds(101, 94, 144, 25);
		frame.getContentPane().add(btnClearResults);
		
//		Button for view the  learning testset
		
		JButton btnViewTestFile = new JButton("View test file");
		btnViewTestFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//textArea.setText("");
				try {
			          BufferedReader input = new BufferedReader(new InputStreamReader(
			              new FileInputStream(file2)));
			          textArea.read(input, "READING FILE :-)");
			        } catch (Exception e) {
			          e.printStackTrace();
			        }
			}
		});
		btnViewTestFile.setBounds(177, 57, 144, 25);
		frame.getContentPane().add(btnViewTestFile);
		
//		most important button
		
		JButton btnNewButton_2 = new JButton("RUN");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				
				
				
				// Cross validation + J45
				if (A1.isSelected() && B1.isSelected()) {
					
					try {

						// Create J48 classifier by
						// creating object of J48 class
						J48 j48Classifier = new J48();

						// Dataset path
						String DataStes
							= file.getPath();

						// Creating bufferedreader to read the dataset
						BufferedReader bufferedReader
							= new BufferedReader(
								new FileReader(DataStes));

						// Create dataset instances
						Instances datasetInstances
							= new Instances(bufferedReader);

						// Set Target Class
						datasetInstances.setClassIndex(
							datasetInstances.numAttributes() - 1);

						// Evaluating by creating object of Evaluation
						// class
						Evaluation evaluation
							= new Evaluation(datasetInstances);

						// Cross Validate Model with 10 folds
						evaluation.crossValidateModel(
							j48Classifier, datasetInstances, 10,
							new Random(1));

						System.out.println(evaluation.toSummaryString(
							"\nResults", false));
						textArea.setText(evaluation.toSummaryString("Cross validation + J45 Evaluation Results : \n", false));
					}

					// Catch block to handle the exceptions
					catch (Exception e) {

						// Print message on the console
						System.out.println("Error Occurred!!!! \n"
										+ e.getMessage());
					}
					
					
					
				}
				// Cross validation + RandomForest
				else if (A2.isSelected() && B1.isSelected()) {
					
					try {

						// Create RandomForest classifier by
						// creating object of RandomForest class
						RandomForest RandomForestClassifier = new RandomForest();

						// Dataset path
						String DataStes
							= file.getPath();

						// Creating bufferedreader to read the dataset
						BufferedReader bufferedReader
							= new BufferedReader(
								new FileReader(DataStes));

						// Create dataset instances
						Instances datasetInstances
							= new Instances(bufferedReader);

						// Set Target Class
						datasetInstances.setClassIndex(
							datasetInstances.numAttributes() - 1);

						// Evaluating by creating object of Evaluation
						// class
						Evaluation evaluation
							= new Evaluation(datasetInstances);

						// Cross Validate Model with 10 folds
						evaluation.crossValidateModel(
								RandomForestClassifier, datasetInstances, 10,
							new Random(1));

						System.out.println(evaluation.toSummaryString(
							"\nResults", false));
						textArea.setText(evaluation.toSummaryString("Cross validation + RandomForest Evaluation Results : \n", false));
					}

					// Catch block to handle the exceptions
					catch (Exception e) {

						// Print message on the console
						System.out.println("Error Occurred!!!! \n"
										+ e.getMessage());
					}
					
					
				}
				// Cross validation + NaiveBayes
				else if (A3.isSelected() && B1.isSelected()) {
					

					try {

						// Create NaiveBayes classifier by
						// creating object of NaiveBayes class
						NaiveBayes NaiveBayesClassifier = new NaiveBayes();

						// Dataset path
						String DataStes
							= file.getPath();

						// Creating bufferedreader to read the dataset
						BufferedReader bufferedReader
							= new BufferedReader(
								new FileReader(DataStes));

						// Create dataset instances
						Instances datasetInstances
							= new Instances(bufferedReader);

						// Set Target Class
						datasetInstances.setClassIndex(
							datasetInstances.numAttributes() - 1);

						// Evaluating by creating object of Evaluation
						// class
						Evaluation evaluation
							= new Evaluation(datasetInstances);

						// Cross Validate Model with 10 folds
						evaluation.crossValidateModel(
								NaiveBayesClassifier, datasetInstances, 10,
							new Random(1));

						System.out.println(evaluation.toSummaryString(
							"\nResults", false));
						textArea.setText(evaluation.toSummaryString("Cross validation + NaiveBayes Evaluation Results : \n", false));
					}

					// Catch block to handle the exceptions
					catch (Exception e) {

						// Print message on the console
						System.out.println("Error Occurred!!!! \n"
										+ e.getMessage());
					}
					
					
					
					
					
				}
				
				// Cross validation + One Rule
				else if (A4.isSelected() && B1.isSelected()) {
					
					
					try {

						// Create One Rule classifier by
						// creating object of One Rule class
						OneR OneRClassifier = new OneR();

						// Dataset path
						String DataStes
							= file.getPath();

						// Creating bufferedreader to read the dataset
						BufferedReader bufferedReader
							= new BufferedReader(
								new FileReader(DataStes));

						// Create dataset instances
						Instances datasetInstances
							= new Instances(bufferedReader);

						// Set Target Class
						datasetInstances.setClassIndex(
							datasetInstances.numAttributes() - 1);

						// Evaluating by creating object of Evaluation
						// class
						Evaluation evaluation
							= new Evaluation(datasetInstances);

						// Cross Validate Model with 10 folds
						evaluation.crossValidateModel(
								OneRClassifier, datasetInstances, 10,
							new Random(1));

						System.out.println(evaluation.toSummaryString(
							"\nResults", false));
						textArea.setText(evaluation.toSummaryString("Cross validation + One Rule Evaluation Results : \n", false));
					}

					// Catch block to handle the exceptions
					catch (Exception e) {

						// Print message on the console
						System.out.println("Error Occurred!!!! \n"
										+ e.getMessage());
					}
					
					
					
					
				}
				
				// Test File + J45
				else if (A1.isSelected() && B2.isSelected()) {
					
					
					try {

						// Create J48 classifier by
						// creating object of J48 class
						J48 j48Classifier = new J48();

						// Dataset path
						String DataStes
							= file.getPath();
						// testset path
						String stastes2
							= file2.getPath();

						// Creating bufferedreader to read the dataset
						BufferedReader bufferedReader
							= new BufferedReader(
								new FileReader(DataStes));
						
						// Creating bufferedreader to read the testset
						BufferedReader bufferedReader2
							= new BufferedReader(
								new FileReader(stastes2));

						// Create dataset instances
						Instances datasetInstances
							= new Instances(bufferedReader);
						
						// Create test instances
						Instances testsetInstances
							= new Instances(bufferedReader);

						// Set Target Class
						datasetInstances.setClassIndex(
							datasetInstances.numAttributes() - 1);

						// Evaluating by creating object of Evaluation
						// class
						Evaluation evaluation
							= new Evaluation(datasetInstances);

						// Cross Validate Model with testset
						evaluation.evaluateModel(j48Classifier, testsetInstances);
						
						

						System.out.println(evaluation.toSummaryString(
							"\nResults", false));
						textArea.setText(evaluation.toSummaryString("Test Set + J45 Evaluation Results : \n", false));
					}

					// Catch block to handle the exceptions
					catch (Exception e) {

						// Print message on the console
						System.out.println("Error Occurred!!!! \n"
										+ e.getMessage());
					}
					
					
					
					
				}
				
				
				// Test File + RandomForest
				else if (A2.isSelected() && B2.isSelected()) {
					
					
					try {

						// Create RandomForest classifier by
						// creating object of RandomForest class
						RandomForest RandomForestClassifier = new RandomForest();

						// Dataset path
						String DataStes
							= file.getPath();
						// testset path
						String stastes2
							= file2.getPath();

						// Creating bufferedreader to read the dataset
						BufferedReader bufferedReader
							= new BufferedReader(
								new FileReader(DataStes));
						
						// Creating bufferedreader to read the testset
						BufferedReader bufferedReader2
							= new BufferedReader(
								new FileReader(stastes2));

						// Create dataset instances
						Instances datasetInstances
							= new Instances(bufferedReader);
						
						// Create test instances
						Instances testsetInstances
							= new Instances(bufferedReader);

						// Set Target Class
						datasetInstances.setClassIndex(
							datasetInstances.numAttributes() - 1);

						// Evaluating by creating object of Evaluation
						// class
						Evaluation evaluation
							= new Evaluation(datasetInstances);

						// Cross Validate Model with testset
						evaluation.evaluateModel(RandomForestClassifier, testsetInstances);
						
						

						System.out.println(evaluation.toSummaryString(
							"\nResults", false));
						textArea.setText(evaluation.toSummaryString("Test Set + RandomForest Evaluation Results : \n", false));
					}

					// Catch block to handle the exceptions
					catch (Exception e) {

						// Print message on the console
						System.out.println("Error Occurred!!!! \n"
										+ e.getMessage());
					}
					
					
					
				}
				
				// Test File + NaiveBayes
				else if (A3.isSelected() && B2.isSelected()) {
					
					
					try {

						// Create NaiveBayes classifier by
						// creating object of NaiveBayes class
						NaiveBayes NaiveBayesClassifier = new NaiveBayes();

						// Dataset path
						String DataStes
							= file.getPath();
						// testset path
						String stastes2
							= file2.getPath();

						// Creating bufferedreader to read the dataset
						BufferedReader bufferedReader
							= new BufferedReader(
								new FileReader(DataStes));
						
						// Creating bufferedreader to read the testset
						BufferedReader bufferedReader2
							= new BufferedReader(
								new FileReader(stastes2));

						// Create dataset instances
						Instances datasetInstances
							= new Instances(bufferedReader);
						
						// Create test instances
						Instances testsetInstances
							= new Instances(bufferedReader);

						// Set Target Class
						datasetInstances.setClassIndex(
							datasetInstances.numAttributes() - 1);

						// Evaluating by creating object of Evaluation
						// class
						Evaluation evaluation
							= new Evaluation(datasetInstances);

						// Cross Validate Model with testset
						evaluation.evaluateModel(NaiveBayesClassifier, testsetInstances);
						
						

						System.out.println(evaluation.toSummaryString(
							"\nResults", false));
						textArea.setText(evaluation.toSummaryString("Test Set + NaiveBayes Evaluation Results : \n", false));
					}

					// Catch block to handle the exceptions
					catch (Exception e) {

						// Print message on the console
						System.out.println("Error Occurred!!!! \n"
										+ e.getMessage());
					}
					
				}
				
				
				// Test File + One Rule
				else if (A4.isSelected() && B2.isSelected()) {
					
					try {

						// Create  One Rule classifier by
						// creating object of OneR class
						OneR OneRClassifier = new OneR();

						// Dataset path
						String DataStes
							= file.getPath();
						// testset path
						String stastes2
							= file2.getPath();

						// Creating bufferedreader to read the dataset
						BufferedReader bufferedReader
							= new BufferedReader(
								new FileReader(DataStes));
						
						// Creating bufferedreader to read the testset
						BufferedReader bufferedReader2
							= new BufferedReader(
								new FileReader(stastes2));

						// Create dataset instances
						Instances datasetInstances
							= new Instances(bufferedReader);
						
						// Create test instances
						Instances testsetInstances
							= new Instances(bufferedReader);

						// Set Target Class
						datasetInstances.setClassIndex(
							datasetInstances.numAttributes() - 1);

						// Evaluating by creating object of Evaluation
						// class
						Evaluation evaluation
							= new Evaluation(datasetInstances);

						// Cross Validate Model with testset
						evaluation.evaluateModel(OneRClassifier, testsetInstances);
						
						

						System.out.println(evaluation.toSummaryString(
							"\nResults", false));
						textArea.setText(evaluation.toSummaryString("Test Set + OneR Evaluation Results : \n", false));
					}

					// Catch block to handle the exceptions
					catch (Exception e) {

						// Print message on the console
						System.out.println("Error Occurred!!!! \n"
										+ e.getMessage());
					}
					
				}
			}
		});
		btnNewButton_2.setBounds(731, 137, 117, 25);
		frame.getContentPane().add(btnNewButton_2);
		

		

		


		
	}
}
