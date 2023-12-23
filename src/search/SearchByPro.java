package search;

import java.util.ArrayList;

import model.StudyProgram;
import model.TreeNode;

public class SearchByPro {

    public ArrayList<StudyProgram> search(TreeNode root, String selectedProgramName) {
        ArrayList<StudyProgram> resultList = new ArrayList<>();
        searchInTreeNode(root, resultList, selectedProgramName);
        return resultList;
    }

    private void searchInTreeNode(TreeNode node, ArrayList<StudyProgram> resultList, String selectedProgramName) {
        if (node == null) {
            return;
        }

        if (node.getProgram() != null) {
            StudyProgram programNode = node.getProgram();
            if (matchesSearchCriteria(programNode, selectedProgramName)) {
                resultList.add(programNode);
            }
        }
        for (TreeNode childNode : node.getChildren()) {
            searchInTreeNode(childNode, resultList, selectedProgramName);
        }
    }

    private boolean matchesSearchCriteria(StudyProgram program, String selectedProgramName) {
        return program != null &&
               (selectedProgramName == null || program.getProgramName().toLowerCase().contains(selectedProgramName.toLowerCase()));
    }
}

