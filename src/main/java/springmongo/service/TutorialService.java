package springmongo.service;

import springmongo.model.Tutorial;

import java.util.List;

public interface TutorialService {
    public Tutorial create(Tutorial tutorial);
    public List<Tutorial> findAll();
    public Tutorial findById(String id);
    public Tutorial update (String id, Tutorial tutorial);
    public boolean delete(String id);

}
