import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import bit.BitTransform;
import faultmodel.mgf.MGF;
import faultmodel.mgf.MGFException;
import reversiblecircuit.nctlibrary.Circuit;
import reversiblecircuit.nctlibrary.CircuitException;
import testset.Testset;
import testset.TestsetException;

public class RC {
	static class Frame {
		JFrame newFrame;

		Frame(String title) {
			newFrame = new JFrame();
			newFrame.setTitle(title);
			newFrame.setLayout(null);
			newFrame.setSize(1600, 900);
			newFrame.setVisible(true);
			newFrame.getContentPane().setBackground(new java.awt.Color(7, 74, 51));
		}

		public void close() {
			newFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			newFrame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					int option = JOptionPane.showConfirmDialog(newFrame, "Are you sure?", "Close Information",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (option == JOptionPane.YES_OPTION)
						newFrame.toBack();
				}
			});
		}
	}

	static class Label {
		JLabel newLabel = new JLabel();

		Label(String title, int width, int height, int fontSize) {
			newLabel.setText(title);
			newLabel.setSize(width, height);
			newLabel.setFont(new Font("Cambria", Font.BOLD + Font.ITALIC, fontSize));
			newLabel.setForeground(Color.WHITE);
		}
	}

	static class Button {
		JButton newButton = new JButton();

		Button(String title, int x, int y, int fontSize) {
			newButton.setText(title);
			newButton.setBounds(x, y, 200, 50);
			newButton.setFont(new Font("Cambria", Font.BOLD + Font.ITALIC, fontSize));
		}
	}

	static class TextField {
		JTextField newTextField = new JTextField();

		TextField(int x, int y) {
			newTextField.setBounds(x, y, 100, 20);
		}
	}

	static class Table {
		JTable newTable;

		Table(int row, int col) {
			newTable = new JTable(row, col);
			SetTable(col);
		}

		Table(Integer[][] matrix, String[] column, int col) {
			newTable = new JTable(matrix, column);
			SetTable(col);
		}

		public void SetTable(int col) {
			newTable.setFont(new Font("Cambria", Font.BOLD + Font.ITALIC, 16));
			newTable.setRowHeight(25);
			newTable.setCellSelectionEnabled(true);
//            newTable.setGridColor(Color.blue);

			JTableHeader tableHeader = newTable.getTableHeader();
//            tableHeader.setForeground(new java.awt.Color(67, 67, 134));
			tableHeader.setFont(new Font("Cambria", Font.BOLD + Font.ITALIC, 20));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < col; i++)
				newTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		public boolean validation(int InputValue, int GateValue, int lower, int upper) {
			for (int i = 0; i < InputValue; i++) {
				for (int j = 0; j < GateValue; j++) {
					if (newTable.getValueAt(i, j) != null) {
						int value = Integer.parseInt((String) newTable.getValueAt(i, j));
						if (value < lower || value > upper)
							return true;
					}
				}
			}
			return false;
		}

		public boolean valid(int InputValue, int GateValue) {
			int count = 0;
			for (int i = 0; i < GateValue; i++) {
				for (int j = 0; j < InputValue; j++) {
					if (newTable.getValueAt(j, i) != null) {
						int value = Integer.parseInt((String) newTable.getValueAt(j, i));
						if (value == 2)
							count = count + 1;
					}
				}
				if (count == 1)
					return true;
			}
			return false;
		}

		public boolean emptyCell(int InputValue, int GateValue) {
			for (int i = 0; i < InputValue; i++) {
				for (int j = 0; j < GateValue; j++) {
					if (newTable.getValueAt(i, j) == null)
						return true;
				}
			}
			return false;
		}

		public void store(int InputValue, int GateValue, Integer[][] circuitData) {
			for (int i = 0; i < InputValue; i++) {
				for (int j = 0; j < GateValue; j++)
					circuitData[i][j] = Integer.parseInt((String) newTable.getValueAt(i, j));

			}
		}
	}

	static class RadioButton {
		JRadioButton newradioButton = new JRadioButton();

		RadioButton(String title, int y) {
			newradioButton.setText(title);
			newradioButton.setBounds(100, y, 300, 40);
			newradioButton.setFont(new Font("Cambria", Font.BOLD + Font.ITALIC, 16));
			newradioButton.setBackground(new java.awt.Color(7, 74, 51));
			newradioButton.setForeground(Color.WHITE);
		}
	}

	static Integer[][] circuitdata = null;
	static Integer[][] testsetdata = null;
	static String faultmodeldata = null;
	static boolean circuitflag = false, testsetflag = false, faultmodelflag = false;
	
	static Circuit createCircuit(Integer[][] data) throws CircuitException {
		int row = data.length;
		int col = data[0].length;
		Circuit cir = null;
		for (int c = 0; c < col; c++) {
			int[] gate = new int[row];
			for (int r = 0; r < row; r++) {
				gate[r] = data[r][c];
			}
			if (c == 0)
				cir = new Circuit(gate);
			else
				cir.add(gate);
		}
		return cir;
	}

	static Testset createTestset(Integer[][] data) throws TestsetException {
		int row = data.length;
		int col = data[0].length;
		Testset tSet = null;
		for (int c = 0; c < col; c++) {
			boolean[] tVec = new boolean[row];
			for (int r = 0; r < row; r++) {
				tVec[r] = BitTransform.intToBool(data[r][c]);
			}
			if (c == 0)
				tSet = new Testset(tVec);
			else
				tSet.add(tVec);
		}
		return tSet;
	}

	public static void createcircuit(int row, int col) {
		circuitdata = new Integer[row][col];
	}

	public static void createtestset(int row, int col) {
		testsetdata = new Integer[row][col];
	}

	public static void main(String[] args) {
		Frame mainFrame = new Frame("Input GUI of Reversible Circuit");
		mainFrame.newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Label title = new Label("Give the inputs for checking the faults in Reversible Circuit - ", 900, 150, 28);
		title.newLabel.setHorizontalAlignment(JLabel.CENTER);
		mainFrame.newFrame.add(title.newLabel);

		Button circuit = new Button("Circuit Input", 350, 200, 16);
		mainFrame.newFrame.add(circuit.newButton);

		Button test_set = new Button("Test Set Input", 350, 290, 16);
		mainFrame.newFrame.add(test_set.newButton);

		Button fault_model = new Button("Fault Model Input", 350, 380, 16);
		mainFrame.newFrame.add(fault_model.newButton);

		Button check = new Button("Check The Circuit", 650, 290, 16);
		mainFrame.newFrame.add(check.newButton);

		circuit.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame circuitFrame = new Frame("Circuit Input of Reversible Circuit");
				circuitFrame.close();

				Label clabel = new Label("Enter The Number Of Input Lines And Gate Lines - ", 700, 100, 28);
				clabel.newLabel.setHorizontalAlignment(JLabel.LEFT);
				circuitFrame.newFrame.add(clabel.newLabel);

				Label InputLines = new Label("Input Lines - ", 200, 200, 16);
				clabel.newLabel.setHorizontalAlignment(JLabel.LEFT);
				circuitFrame.newFrame.add(InputLines.newLabel);

				Label GateLines = new Label("Gate - ", 200, 300, 16);
				clabel.newLabel.setHorizontalAlignment(JLabel.LEFT);
				circuitFrame.newFrame.add(GateLines.newLabel);

				TextField InputField = new TextField(110, 93);
				circuitFrame.newFrame.add(InputField.newTextField);

				TextField GateField = new TextField(110, 144);
				circuitFrame.newFrame.add(GateField.newTextField);

				Button create = new Button("Create Table", 320, 100, 16);
				circuitFrame.newFrame.add(create.newButton);

				Label ctitle = new Label("Enter The Values Of The Table - ", 500, 500, 28);
				ctitle.newLabel.setHorizontalAlignment(JLabel.LEFT);
				ctitle.newLabel.setVisible(false);
				circuitFrame.newFrame.add(ctitle.newLabel);

				Button save = new Button("Save", 500, 550, 16);
				save.newButton.setVisible(false);
				circuitFrame.newFrame.add(save.newButton);

				Button submit = new Button("Submit", 200, 550, 16);
				submit.newButton.setVisible(false);
				circuitFrame.newFrame.add(submit.newButton);

				create.newButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int circuitInputValue = Integer.parseInt(InputField.newTextField.getText());
						int GateValue = Integer.parseInt(GateField.newTextField.getText());

						ctitle.newLabel.setVisible(true);

						Label title0 = new Label("0 -> Null Input", 200, 650, 20);
						title0.newLabel.setHorizontalAlignment(JLabel.LEFT);
						circuitFrame.newFrame.add(title0.newLabel);

						Label title1 = new Label("1 -> Control Input", 200, 750, 20);
						title1.newLabel.setHorizontalAlignment(JLabel.LEFT);
						circuitFrame.newFrame.add(title1.newLabel);

						Label title2 = new Label("2 -> Target Input", 200, 850, 20);
						title2.newLabel.setHorizontalAlignment(JLabel.LEFT);
						circuitFrame.newFrame.add(title2.newLabel);

						if (testsetflag == true && testsetdata.length != circuitInputValue)
							JOptionPane.showMessageDialog(null, "circuit not compatible");
						else {
							Table table = new Table(circuitInputValue, GateValue);
							JScrollPane pane = new JScrollPane(table.newTable);
							pane.setBounds(200, 310, 300, 160);
							circuitFrame.newFrame.getContentPane().add(pane);

							save.newButton.setVisible(true);

							submit.newButton.setVisible(true);

							createcircuit(circuitInputValue, GateValue);

							save.newButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if (table.validation(circuitInputValue, GateValue, 0, 2))
										JOptionPane.showMessageDialog(null, "Enter only 0, 1 and 2", "Error",
												JOptionPane.ERROR_MESSAGE);
									else if (table.valid(circuitInputValue, GateValue))
										JOptionPane.showMessageDialog(null, "each gate must be only one target value",
												"Error", JOptionPane.ERROR_MESSAGE);
									else if (table.emptyCell(circuitInputValue, GateValue))
										JOptionPane.showMessageDialog(null, "Cell cannot be empty", "Error",
												JOptionPane.ERROR_MESSAGE);
									else {
										table.store(circuitInputValue, GateValue, circuitdata);
										JOptionPane.showMessageDialog(null, "Saved Successfully");
										circuitflag = true;
									}
								}
							});

							submit.newButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									circuitFrame.newFrame.toBack();
								}
							});
						}
					}
				});
			}
		});

		test_set.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame testSetFrame = new Frame("Test Set Input of Reversible Circuit");
				testSetFrame.close();

				Label tslabel = new Label("Enter the Input Lines And Number of Test Set - ", 700, 100, 28);
				tslabel.newLabel.setHorizontalAlignment(JLabel.LEFT);
				testSetFrame.newFrame.add(tslabel.newLabel);

				Label InputLines = new Label("Input Lines - ", 200, 200, 16);
				tslabel.newLabel.setHorizontalAlignment(JLabel.LEFT);
				testSetFrame.newFrame.add(InputLines.newLabel);

				Label TestSet = new Label("Number of Test - ", 200, 300, 16);
				tslabel.newLabel.setHorizontalAlignment(JLabel.LEFT);
				testSetFrame.newFrame.add(TestSet.newLabel);

				TextField InputField = new TextField(160, 93);
				testSetFrame.newFrame.add(InputField.newTextField);

				TextField TestSetField = new TextField(160, 144);
				testSetFrame.newFrame.add(TestSetField.newTextField);

				Button create = new Button("Create Table", 350, 100, 16);
				testSetFrame.newFrame.add(create.newButton);

				Label tstitle = new Label("Enter The Values Of The Table - ", 500, 500, 28);
				tstitle.newLabel.setVisible(false);
				testSetFrame.newFrame.add(tstitle.newLabel);

				Button save = new Button("Save", 500, 600, 16);
				save.newButton.setVisible(false);
				testSetFrame.newFrame.add(save.newButton);

				Button submit = new Button("Submit", 200, 600, 16);
				submit.newButton.setVisible(false);
				testSetFrame.newFrame.add(submit.newButton);

				create.newButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int testsetInputValue = Integer.parseInt(InputField.newTextField.getText());
						int TestSetValue = Integer.parseInt(TestSetField.newTextField.getText());

						if (circuitflag == true && circuitdata.length != testsetInputValue)
							JOptionPane.showMessageDialog(null, "testset not compatible");
						else {
							tstitle.newLabel.setVisible(true);

							Table table = new Table(testsetInputValue, TestSetValue);
							JScrollPane pane = new JScrollPane(table.newTable);
							pane.setBounds(200, 350, 300, 160);
							testSetFrame.newFrame.getContentPane().add(pane);

							save.newButton.setVisible(true);

							submit.newButton.setVisible(true);

							createtestset(testsetInputValue, TestSetValue);

							save.newButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if (table.validation(testsetInputValue, TestSetValue, 0, 1))
										JOptionPane.showMessageDialog(null, "Enter only 0 and 1", "Error",
												JOptionPane.ERROR_MESSAGE);
									else if (table.emptyCell(testsetInputValue, TestSetValue))
										JOptionPane.showMessageDialog(null, "Cell cannot be empty", "Error",
												JOptionPane.ERROR_MESSAGE);
									else {
										table.store(testsetInputValue, TestSetValue, testsetdata);
										JOptionPane.showMessageDialog(null, "Saved Successfully");
										testsetflag = true;
									}
								}
							});

							submit.newButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									testSetFrame.newFrame.toBack();
								}
							});
						}
					}
				});
			}
		});

		fault_model.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame faultModelFrame = new Frame("Fault Model Input of Reversible Circuit");
				faultModelFrame.close();

				Label choose = new Label("Choose the Fault Model from Below - ", 700, 100, 28);
				faultModelFrame.newFrame.add(choose.newLabel);

				RadioButton MGFM = new RadioButton("Missing Gate Fault Model", 200);
				faultModelFrame.newFrame.add(MGFM.newradioButton);

				RadioButton SAFM = new RadioButton("Stuck-At Fault Model", 300);
				faultModelFrame.newFrame.add(SAFM.newradioButton);

				ButtonGroup singleSelection = new ButtonGroup();
				singleSelection.add(MGFM.newradioButton);
				singleSelection.add(SAFM.newradioButton);

				Button save = new Button("Save", 400, 450, 16);
				faultModelFrame.newFrame.add(save.newButton);

				Button submit = new Button("Submit", 100, 450, 16);
				faultModelFrame.newFrame.add(submit.newButton);

				save.newButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (MGFM.newradioButton.isSelected())
							faultmodeldata = "Missing Gate Fault Model";
						else if (SAFM.newradioButton.isSelected())
							faultmodeldata = "Stuck-At Fault Model";
						else
							JOptionPane.showMessageDialog(null, "Select a fault Model");
						if (faultmodeldata != null) {
							JOptionPane.showMessageDialog(null, "Saved Successfully");
							faultmodelflag = true;
						}
					}
				});

				submit.newButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						faultModelFrame.newFrame.toBack();
					}
				});
			}
		});

		check.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (circuitflag && testsetflag && faultmodelflag) {
					Frame checkFrame = new Frame("Output GUI Of Reversible Circuit");
					checkFrame.close();

					Label circuit = new Label("Circuit - ", 900, 100, 28);
					circuit.newLabel.setHorizontalAlignment(JLabel.LEFT);
					checkFrame.newFrame.add(circuit.newLabel);

					int circuitcol = circuitdata[0].length;
					String[] circuitcolumn = new String[circuitcol];
					for (int i = 0; i < circuitcol; i++)
						circuitcolumn[i] = "Gate line" + i;

					Table cTable = new Table(circuitdata, circuitcolumn, circuitcol);
					JScrollPane circuitpane = new JScrollPane(cTable.newTable);
					circuitpane.setBounds(50, 80, 200, 100);
					checkFrame.newFrame.getContentPane().add(circuitpane);

					Label testset = new Label("Test Set - ", 900, 100, 28);
					testset.newLabel.setHorizontalAlignment(JLabel.CENTER);
					checkFrame.newFrame.add(testset.newLabel);

					int testsetcol = testsetdata[0].length;
					String[] testsetcolumn = new String[testsetcol];
					for (int i = 0; i < testsetcol; i++)
						testsetcolumn[i] = "Test Set" + i;

					Table tsTable = new Table(testsetdata, testsetcolumn, testsetcol);
					JScrollPane testsetpane = new JScrollPane(tsTable.newTable);
					testsetpane.setBounds(460, 80, 200, 100);
					checkFrame.newFrame.getContentPane().add(testsetpane);

					Label faultmodel = new Label("Fault Model - ", 900, 100, 28);
					faultmodel.newLabel.setHorizontalAlignment(JLabel.RIGHT);
					checkFrame.newFrame.add(faultmodel.newLabel);

					Label fm = new Label(faultmodeldata, 1100, 200, 24);
					fm.newLabel.setHorizontalAlignment(JLabel.RIGHT);
					checkFrame.newFrame.add(fm.newLabel);

					
					
					try {
						String outputString;
						if(faultmodeldata.equals("Stuck-At Fault Model"))
							outputString = "Under construction";
						else {
							Circuit newCircuit = createCircuit(circuitdata);
							Testset newTestset = createTestset(testsetdata);
							MGF mgfObj = new MGF(newCircuit, newTestset);
							mgfObj.test();
							outputString = "<html>" + mgfObj.toString().replaceAll("\n", "<br>");
							
						}
						Label output = new Label(outputString, 900, 500, 28);
						output.newLabel.setHorizontalAlignment(JLabel.LEFT);
						checkFrame.newFrame.add(output.newLabel);
					} catch(CircuitException | TestsetException exp) {
						exp.printStackTrace();
					} catch (MGFException exp) {
						exp.printStackTrace();
					}
					
				} else
					JOptionPane.showMessageDialog(null, "complete the inputs");
			}
		});
	}
}
