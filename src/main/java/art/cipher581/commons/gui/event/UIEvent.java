package art.cipher581.commons.gui.event;


import java.awt.Component;
import java.sql.Timestamp;


public class UIEvent {

	private Timestamp time;

	private String name;

	private Object value;
	
	private Component source;


	public UIEvent(String name, Component source) {
		this(name, null, source);
	}
	
	public UIEvent(String name, Object value, Component source) {
		super();

		this.name = name;
		this.value = value;
		this.source = source;
		this.time = new Timestamp(System.currentTimeMillis());
	}


	public Timestamp getTime() {
		return time;
	}


	public void setTime(Timestamp time) {
		this.time = time;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}

	
	public Component getSource() {
		return source;
	}

	
	public void setSource(Component source) {
		this.source = source;
	}


	@Override
	public String toString() {
		return name + " (value=" + value + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UIEvent other = (UIEvent) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

}
