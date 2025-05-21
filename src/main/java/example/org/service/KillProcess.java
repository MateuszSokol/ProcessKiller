package example.org.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KillProcess {
    public boolean killProcessByName(String processName) {
        try {
            Process listProcess = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(listProcess.getInputStream()));

            boolean isRunning = false;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(processName.toLowerCase())) {
                    isRunning = true;
                    break;
                }
            }

            if (isRunning) {
                Process killProcess = Runtime.getRuntime().exec("taskkill /F /IM " + processName);
                killProcess.waitFor();
                System.out.println("Proces " + processName + " został zakończony.");
                return true;
            } else {
                System.out.println("Proces " + processName + " nie działa.");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
