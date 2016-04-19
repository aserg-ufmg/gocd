package movedclasses;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.thoughtworks.go.util.ArrayUtil;

public abstract class ExtractedSuperClass extends AppenderSkeleton {

	protected List<String> messages = new ArrayList<String>();
	protected List<LoggingEvent> events = new ArrayList<LoggingEvent>();

	public ExtractedSuperClass() {
		super();
	}

	public void close() {
	}

	public boolean requiresLayout() {
	    return false;
	}

	public String[] getMessages() {
	    return (String[]) messages.toArray(new String[messages.size()]);
	}

	public void clear() {
	    messages.clear();
	}

	public String getLog() {
	    return ArrayUtil.join(getMessages());
	}

}