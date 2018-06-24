//Created by: Madison Pahl

import java.util.Comparator;

public class Comparison implements Comparator<RobotState> {
	@Override
	  public int compare (RobotState a, RobotState b)
	  {
	    // Check if either state is null

	    if (a.getEvaluation() < b.getEvaluation())
	    {
	      return -1;
	    }
	    if (a.getEvaluation() > b.getEvaluation())
	    {
	      return 1;
	    }
	    return 0;
	  }
}
