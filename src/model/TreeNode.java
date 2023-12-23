package model;

import java.util.ArrayList;

public class TreeNode {
    private University uni;
    private StudyProgram pro;
    private ArrayList<TreeNode> children;
    
    public TreeNode() {
        this.children = new ArrayList<>();
    }

    public TreeNode(University uni) {
        this.uni = uni;
        this.children = new ArrayList<>();
    }

    public TreeNode(StudyProgram pro) {
        this.pro = pro;
        this.children = new ArrayList<>();
    }

    public University getUniversity() {
        return uni;
    }

    public StudyProgram getProgram() {
        return pro;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }
}