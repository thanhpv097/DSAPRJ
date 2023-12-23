package model;

import java.util.ArrayList;

public class SMModel {
    private TreeNode root;
    public SMModel() {
        this.root = new TreeNode();
    }
    
    public void setProgramTree(ArrayList<StudyProgram> programs) {
    	for(StudyProgram program : programs) {
    		insert(root, program);
    	}
    }
    
    public TreeNode getProgram() {
    	return root;
    }
    
    public void insert(TreeNode root, StudyProgram program) {
        University university = program.getUniversity();
        TreeNode universityNode = findUniversityNode(root, university);
        StudyProgram programs = checkExists(universityNode, program);

        if (universityNode == null) {
            universityNode = new TreeNode(university);
            root.addChild(universityNode);
        }
        if (programs == null) {
            TreeNode programNode = new TreeNode(program);
            universityNode.addChild(programNode);
        }
    }

    public void delete(TreeNode node, StudyProgram program) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.getChildren().size(); i++) {
            TreeNode child = node.getChildren().get(i);
            if (child.getProgram() != null && child.getProgram().getProgramCode().equals(program.getProgramCode())) {
                node.getChildren().remove(i);
                return;
            }
            delete(child, program);
        }
    }
    
    public StudyProgram checkExists(TreeNode node, StudyProgram program) {
        if (node == null) {
            return null;
        }
        for (TreeNode child : node.getChildren()) {
            if (child.getProgram() != null && child.getProgram().getProgramCode().equals(program.getProgramCode())) {
                return child.getProgram();
            }
            StudyProgram foundProgram = checkExists(child, program);
            if (foundProgram != null) {
                return foundProgram;
            }
        }
        return null;
    }
    
    public StudyProgram getExistingProgram(StudyProgram program) {
    	 return checkExists(root, program);
    }
    
    public TreeNode findUniversityNode(TreeNode node, University university) {
        if (node.getUniversity() != null && node.getUniversity().getUniversityName().equals(university.getUniversityName())) {
            return node;
        }
        for (TreeNode child : node.getChildren()) {
            TreeNode foundNode = findUniversityNode(child, university);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }
}
