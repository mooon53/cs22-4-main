package dao;

import java.util.HashMap;

public enum PortHolder {
	INSTANCE;

	private static final HashMap<Integer, Process> usedPorts = new HashMap<>();

	public int firstUnusedPort() {
		for (int i = 8000; i < 9000; i++) {
			if (!usedPorts.containsKey(i) || !usedPorts.get(i).isAlive()) {
				return i;
			}
		} return 0;
	}

	public void addPort(int port, Process proc) {
		usedPorts.put(port, proc);
	}
}
