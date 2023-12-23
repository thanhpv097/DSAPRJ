package search;

import java.util.ArrayList;

import model.StudyProgram;
import model.TreeNode;
import model.University;

public class SearchByUni {

    public ArrayList<StudyProgram> search(TreeNode root, String selectedUniversityName) {
        ArrayList<StudyProgram> resultList = new ArrayList<>();
        searchInTreeNode(root, resultList, selectedUniversityName);
        return resultList;
    }

    private void searchInTreeNode(TreeNode node, ArrayList<StudyProgram> resultList, String selectedUniversityName) {
        if (node == null) {
            return;
        }

        if (node.getUniversity() != null) {
            University university = node.getUniversity();
            if (matchesSearchCriteria(university, selectedUniversityName)) {
                for (TreeNode childNode : node.getChildren()) {
                    StudyProgram program = childNode.getProgram();
                    if (program != null) {
                        resultList.add(program);
                    }
                }
            }
        }
        for (TreeNode childNode : node.getChildren()) {
            searchInTreeNode(childNode, resultList, selectedUniversityName);
        }
    }

    private boolean matchesSearchCriteria(University university, String selectedUniversityName) {
        return university != null &&
               (selectedUniversityName == null || university.getUniversityName().toLowerCase().contains(selectedUniversityName.toLowerCase()));
    }
}
