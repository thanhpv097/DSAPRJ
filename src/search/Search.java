package search;

import java.util.ArrayList;

import model.SMModel;
import model.StudyProgram;
import view.SMView;

public class Search {
	SMView view;
    SMModel model;

    public Search(SMModel model, SMView view) {
        this.view = view;
        this.model = model;
    }
    public ArrayList<StudyProgram> searchInTree() {
    	String selectedUniversityName = view.textField_SelectedSchoolNameSearch.getText();
        boolean hasSelectedUniversityName = selectedUniversityName.length() > 0;
        String selectedProgramName = view.textField_SelectedProgramNameSearch.getText();
        boolean hasSelectedProgramName = selectedProgramName.length() > 0;
        
        ArrayList<StudyProgram> result = new ArrayList();
        
        if (hasSelectedUniversityName && !hasSelectedProgramName) {
        	SearchByUni searchByUni = new SearchByUni(); 
        	result = searchByUni.search(model.getProgram(), selectedUniversityName);
        }

        if (!hasSelectedUniversityName && hasSelectedProgramName) {
        	SearchByPro searchByPro = new SearchByPro(); 
        	result = searchByPro.search(model.getProgram(), selectedProgramName);
        }

        if (hasSelectedUniversityName && hasSelectedProgramName) {
        	SearchByBoth searchByBoth = new SearchByBoth(); 
        	result = searchByBoth.search(model.getProgram(), selectedUniversityName, selectedProgramName);
        }
        return result;
    }
}
