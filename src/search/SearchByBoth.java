package search;

import java.util.ArrayList;

import model.StudyProgram;
import model.TreeNode;
import model.University;

public class SearchByBoth {

    public ArrayList<StudyProgram> search(TreeNode root, String selectedUniversityName, String selectedProgramName) {
        ArrayList<StudyProgram> resultList = new ArrayList<>();
        searchInTreeNode(root, resultList, selectedUniversityName, selectedProgramName);
        return resultList;
    }

    private void searchInTreeNode(TreeNode node, ArrayList<StudyProgram> resultList,
                                  String selectedUniversityName, String selectedProgramName) {
        if (node == null) {
            return;
        }

        University university = node.getUniversity();
        if (university != null && matchesUniversitySearchCriteria(university, selectedUniversityName)) {
            searchProgramsInUniversity(node, resultList, selectedProgramName);
        }
        for (TreeNode childNode : node.getChildren()) {
            searchInTreeNode(childNode, resultList, selectedUniversityName, selectedProgramName);
        }
    }

    private void searchProgramsInUniversity(TreeNode universityNode, ArrayList<StudyProgram> resultList, String selectedProgramName) {
        for (TreeNode programNode : universityNode.getChildren()) {
            StudyProgram program = programNode.getProgram();
            if (program != null && matchesProgramSearchCriteria(program, selectedProgramName)) {
                resultList.add(program);
            }
        }
    }

    private boolean matchesUniversitySearchCriteria(University university, String selectedUniversityName) {
        return university != null &&
               (selectedUniversityName == null || university.getUniversityName().toLowerCase().contains(selectedUniversityName.toLowerCase()));
    }

    private boolean matchesProgramSearchCriteria(StudyProgram program, String selectedProgramName) {
        return program != null &&
               (selectedProgramName == null || program.getProgramName().toLowerCase().contains(selectedProgramName.toLowerCase()));
    }
}

