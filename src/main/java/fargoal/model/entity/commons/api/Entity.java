package fargoal.model.entity.commons.api;

import fargoal.commons.api.Position;
import fargoal.model.FloorElement;

public interface Entity extends FloorElement{
    
    public Health getHealth();

    public Position getPosition();

    public Integer getSkill();
}
