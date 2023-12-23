package view;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import file.ReadFile;
import file.SaveFile;
import model.SMModel;
import model.StudyProgram;
import model.TreeNode;
import model.University;
import search.Search;
import sort.SortByScore;
import sort.SortByTuition;

public class ControllerView {
	
	SMView view;
	SMModel model;
	Search search;
	ReadFile readFile;
	SaveFile saveFile;
	SortByScore sortByScore;
	SortByTuition sortByTuition;
	DefaultTableModel modelTable;
	TreeNode root;
	
	public ControllerView(SMView view) {
		this.view = view;
		modelTable = (DefaultTableModel) view.table.getModel();
		model = new SMModel();
		readFile = new ReadFile();
		saveFile = new SaveFile(this);
		sortByScore = new SortByScore(this);
		sortByTuition = new SortByTuition(this);
		search = new Search(this, model, view);
		root = model.getProgram();
	}
	
	public ArrayList<StudyProgram> getDataFromTable(DefaultTableModel model) {
	    ArrayList<StudyProgram> studyProgramList = new ArrayList<StudyProgram>();
	    for (int i = 0; i < model.getRowCount(); i++) {
	        String programCode = model.getValueAt(i, 0) + "";
	        String programName = model.getValueAt(i, 1) + "";
	        double admissionScore = Double.valueOf(model.getValueAt(i, 2) + "");
	        String universityCode = model.getValueAt(i, 3) + "";
	        String universityName = model.getValueAt(i, 4) + "";
	        String universityAddress = model.getValueAt(i, 5) + "";
	        University university = new University(universityCode, universityName, universityAddress);
	        long tuitionFee = Long.valueOf((String) model.getValueAt(i, 6));
	        StudyProgram studyProgram = new StudyProgram(programCode, programName, university, tuitionFee, admissionScore);
	        studyProgramList.add(studyProgram);
	    }
	    return studyProgramList;
	}
	public void addDataToTable(TreeNode node) {
	    if (node instanceof model.TreeNode) {
	        model.TreeNode programNode = (model.TreeNode) node;
	        if (programNode.getProgram() != null) {
	            StudyProgram program = programNode.getProgram();
	            modelTable.addRow(new Object[]{
	                    program.getProgramCode(),
	                    program.getProgramName(),
	                    program.getAdmissionScore() + "",
	                    program.getUniversity().getUniversityCode() + "",
	                    program.getUniversity().getUniversityName() + "",
	                    program.getUniversity().getAddress() + "",
	                    program.getTuitionFee() + ""
	            });
	        }
	        for (model.TreeNode child : programNode.getChildren()) {
	            addDataToTable((TreeNode)child);
	        }
	    }
	}
	public void addDataToTable(ArrayList<StudyProgram> studyProgramList) {
	    for (int i = 0; i < studyProgramList.size(); i++) {
	        modelTable.addRow(new Object[] {
	                studyProgramList.get(i).getProgramCode(),
	                studyProgramList.get(i).getProgramName(),
	                studyProgramList.get(i).getAdmissionScore() + "",
	                studyProgramList.get(i).getUniversity().getUniversityCode() + "",
	                studyProgramList.get(i).getUniversity().getUniversityName() + "",
	                studyProgramList.get(i).getUniversity().getAddress() + "",
	                studyProgramList.get(i).getTuitionFee() + ""});
	    }
	}

	public StudyProgram getSelectedProgram() {
	    int rowIndex = view.table.getSelectedRow();

	    String programCode = modelTable.getValueAt(rowIndex, 0) + "";
	    String programName = modelTable.getValueAt(rowIndex, 1) + "";
	    double admissionScore = Double.valueOf(modelTable.getValueAt(rowIndex, 2) + "");
	    String universityCode = University.getUniversityByCode(modelTable.getValueAt(rowIndex, 3) + "");
	    String universityName = University.getUniversityByName(modelTable.getValueAt(rowIndex, 4) + "");
	    String universityAddress = University.getUniversityByAddress(modelTable.getValueAt(rowIndex, 5) + "");
	    long tuitionFee = Long.valueOf((String) modelTable.getValueAt(rowIndex, 6));
	    University university = new University(universityCode, universityName, universityAddress);
	    StudyProgram program = new StudyProgram(programCode, programName, university, tuitionFee, admissionScore);
	    return program;
	}

	public void displayProgram() {
	    StudyProgram program = getSelectedProgram();

	    view.textField_ProgramCode.setText(program.getProgramCode());
	    view.textField_ProgramName.setText(program.getProgramName());
	    view.textField_AdmissionScore.setText(program.getAdmissionScore() + "");
	    view.textField_UniversityCode.setText(program.getUniversity().getUniversityCode());
	    view.textField_UniversityName.setText(program.getUniversity().getUniversityName() + "");
	    view.textField_Address.setText(program.getUniversity().getAddress() + "");
	    view.textField_TuitionFee.setText(program.getTuitionFee() + "");
	}

	public void deleteTableRow() {
	    int indexRow = view.table.getSelectedRow();
	    int select = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn xóa không?");
	    if (select == JOptionPane.YES_OPTION) {
	        StudyProgram program = getSelectedProgram();
	        model.delete(root, program);
	        modelTable.removeRow(indexRow);
	    }
	}

	public void sortByScore() {
	    String[] options = {"Sắp xếp tăng dần", "Sắp xếp giảm dần"};
	    int result = JOptionPane.showOptionDialog(
	            null,
	            "Bạn có chắc chắn muốn sắp xếp",
	            "Xác nhận sắp xếp",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            options,
	            options[0]
	    );
	    if (result == JOptionPane.YES_OPTION) {
	        ArrayList<StudyProgram> sortedList = sortByScore.sortAscending(modelTable);
	        modelTable.setRowCount(0);
	        addDataToTable(sortedList);
	    } else if (result == JOptionPane.NO_OPTION) {
	        ArrayList<StudyProgram> sortedList = sortByScore.sortDescending(modelTable);
	        modelTable.setRowCount(0);
	        addDataToTable(sortedList);
	    }
	}

	public void sortByTuition() {
		String[] options = {"Sắp xếp tăng dần", "Sắp xếp giảm dần"};
		int result = JOptionPane.showOptionDialog(
                null,
                "Bạn có chắc chắn muốn sắp xếp?",
                "Xác nhận sắp xếp",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
		if (result == JOptionPane.YES_OPTION) {
	        ArrayList<StudyProgram> sortedList = sortByTuition.sortAscending(modelTable);
	        modelTable.setRowCount(0);
	        addDataToTable(sortedList);
	    } else if (result == JOptionPane.NO_OPTION) {
	        ArrayList<StudyProgram> sortedList = sortByTuition.sortDescending(modelTable);
	        modelTable.setRowCount(0);
	        addDataToTable(sortedList);
	    }
	}
	public void clearFields() {
		view.textField_ProgramCode.setText("");
	    view.textField_ProgramName.setText("");
	    view.textField_AdmissionScore.setText("");
	    view.textField_UniversityCode.setText("");
	    view.textField_UniversityName.setText("");
	    view.textField_Address.setText("");
	    view.textField_TuitionFee.setText("");
	}

	public void addToTable() {
		String programCode = view.textField_ProgramCode.getText() + "";
		String programName = view.textField_ProgramName.getText();
		String universityCode = view.textField_UniversityCode.getText();
		String universityName = view.textField_UniversityName.getText();
		String address = view.textField_Address.getText();
		University university = new University(universityCode, universityName, address);
		long tuitionFee = Long.valueOf(view.textField_TuitionFee.getText());
		double admissionScore = Double.valueOf(view.textField_AdmissionScore.getText());
		StudyProgram studyProgram = new StudyProgram(programCode, programName, university, tuitionFee, admissionScore);
		addOrUpdateToTable(studyProgram, this.model.getExistingProgram(studyProgram));
	}
	public void addOrUpdateToTable(StudyProgram program, StudyProgram existingProgram) {
		StudyProgram checkExit = this.model.checkExists(root, program);
	    if (checkExit == null) {
	        this.model.insert(root, program);
	        modelTable.addRow(new Object[]{
	                program.getProgramCode(),
	                program.getProgramName(),
	                program.getAdmissionScore() + "",
	                program.getUniversity().getUniversityCode() + "",
	                program.getUniversity().getUniversityName() + "",
	                program.getUniversity().getAddress() + "",
	                program.getTuitionFee() + ""
	        });
	        JOptionPane.showMessageDialog(view, "Ngành được lưu thành công");
	    } else {
	        int option = JOptionPane.showOptionDialog(view,
	                "Ngành đã tồn tại. Bạn có muốn cập nhật lại ngành học?",
	                "Thông báo",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE,
	                null,
	                new Object[]{"cập nhật", "Hủy bỏ"},
	                null);

	        if (option == JOptionPane.YES_OPTION) {
	            this.model.delete(root, existingProgram);
	            this.model.insert(root, program);
	            int rowCount = modelTable.getRowCount();
	            for (int i = 0; i < rowCount; i++) {
	                String programCode = modelTable.getValueAt(i, 0) + "";
	                if (programCode.equals(program.getProgramCode() + "")) {
	                    modelTable.setValueAt(program.getProgramCode() + "", i, 0);
	                    modelTable.setValueAt(program.getProgramName() + "", i, 1);
	                    modelTable.setValueAt(program.getAdmissionScore() + "", i, 2);
	                    modelTable.setValueAt(program.getUniversity().getUniversityCode() + "", i, 3);
	                    modelTable.setValueAt(program.getUniversity().getUniversityName() + "", i, 4);
	                    modelTable.setValueAt(program.getUniversity().getAddress() + "", i, 5);
	                    modelTable.setValueAt(program.getTuitionFee() + "", i, 6);
	                }
	            }
	            JOptionPane.showMessageDialog(view, "Ngành đã được cập nhật");
	        }
	    }
	}

	public void cancel() {
	    modelTable.setRowCount(0);
	    addDataToTable(this.root);
	}


	public void disPlayAbout() {
		JOptionPane.showMessageDialog(view, "Phần mềm quản lý trường đại học");
	}

	public void exit() {
		int result = JOptionPane.showConfirmDialog(
                view,
                "Bạn có muốn thoát khỏi chương trình",
                "Exit",
                JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	public void readFile() {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Choose Excel File");
	    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel File", "xlsx"));

	    int result = fileChooser.showOpenDialog(null);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        String filePath = selectedFile.getAbsolutePath();

	        ArrayList<StudyProgram> studyPrograms = readFile.readFile(filePath);
	        this.model.setProgramTree(studyPrograms);
	    }
	}

	public void setDataToTable() {
	    modelTable.setRowCount(0);
	    addDataToTable(root);
	}
	public void saveFile() {
		boolean isFileSaved = false;
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Save As");
	    fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

	    int userSelection = fileChooser.showSaveDialog(null);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
	        if (!filePath.toLowerCase().endsWith(".xlsx")) {
	            filePath += ".xlsx";
	        }
	        isFileSaved = saveFile.saveListToExcel(modelTable, filePath);

	        if (isFileSaved) {
	        	JOptionPane.showMessageDialog(view, "File được lưu thành công");
	        } else {
	        	JOptionPane.showMessageDialog(view, "File không được lưu thành công");
	        }
	    }
	}

	public void search() {
		ArrayList<StudyProgram> list = search.searchInTree();
	    if (list.size() == 0) {
	        JOptionPane.showMessageDialog(view, "Không tìm thấy ngành học", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    } else if (list.size() > 0) {
	        modelTable.setRowCount(0);
	        addDataToTable(list);
	    }
	}
}