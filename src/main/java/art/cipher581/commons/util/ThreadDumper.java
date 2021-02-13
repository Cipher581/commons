package art.cipher581.commons.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


public class ThreadDumper {

	private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
	
	private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public File dump(File dest) throws IOException {
		if (dest == null) {
			dest = getDefaultDestination(new File("."));
		} else if (dest.isDirectory()) {
			dest = getDefaultDestination(dest);
		}
		
		long[] threadIds = threadMXBean.getAllThreadIds();
		ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds, 100);
		
		FileWriter fileWriter = new FileWriter(dest, false);
		
		try {
			dump(fileWriter, threadInfos);
		} finally {
			Safely.close(fileWriter);
		}

		return dest;
	}


	public void dump(Writer writer, ThreadInfo[] threadInfos) throws IOException {
		if (threadInfos != null) {
			String timeFmt = timeFormat.format(new Timestamp(System.currentTimeMillis()));

			writer.write("Threaddump (current time: " + timeFmt + ")"); //$NON-NLS-1$
			writer.write("\n\n"); //$NON-NLS-1$

			writer.write("deadlocked:           " + getThreadNameList(threadMXBean.findDeadlockedThreads()) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write("monitorDeadlocked:    " + getThreadNameList(threadMXBean.findMonitorDeadlockedThreads()) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
			writer.write("\n"); //$NON-NLS-1$
			writer.write("\n"); //$NON-NLS-1$

			for (ThreadInfo threadInfo : threadInfos) {
				// System.out.println(threadInfo);

				writer.write("threadId:       " + threadInfo.getThreadId() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
				writer.write("threadName:     " + threadInfo.getThreadName() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
				writer.write("threadState:    " + threadInfo.getThreadState().toString() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

				writer.write("blockedCount:   " + threadInfo.getBlockedCount() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
				writer.write("blockedTime:    " + threadInfo.getBlockedTime() + "ms\n"); //$NON-NLS-1$ //$NON-NLS-2$

				writer.write("lockInfo:       " + threadInfo.getLockInfo() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
				writer.write("lockName:       " + threadInfo.getLockName() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
				writer.write("lockOwnerId:    " + threadInfo.getLockOwnerId() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
				writer.write("lockOwnerName:  " + threadInfo.getLockOwnerName() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

				writer.write("waitedCount:    " + threadInfo.getWaitedCount() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
				writer.write("waitedTime:     " + threadInfo.getWaitedTime() + "ms\n"); //$NON-NLS-1$ //$NON-NLS-2$

				MonitorInfo[] monitorInfos = threadInfo.getLockedMonitors();
				if (monitorInfos != null && monitorInfos.length > 0) {
					writer.write("monitorInfos:   " + monitorInfos[0] + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

					for (int i = 1; i < monitorInfos.length; i++) {
						writer.write("                " + monitorInfos[i] + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}

				LockInfo[] lockInfos = threadInfo.getLockedSynchronizers();
				if (lockInfos != null && lockInfos.length > 0) {
					writer.write("lockInfos:      " + lockInfos[0] + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

					for (int i = 1; i < lockInfos.length; i++) {
						writer.write("                " + lockInfos[i] + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}

				StackTraceElement[] trace = threadInfo.getStackTrace();
				if (trace != null && trace.length > 0) {
					writer.write("stackTrace:     " + trace[0] + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

					for (int i = 1; i < trace.length; i++) {
						writer.write("                " + trace[i] + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}

				writer.write("\n\n"); //$NON-NLS-1$

				writer.flush();
			}
		} else {
			throw new IllegalStateException("no thread infos available"); //$NON-NLS-1$
		}
	}


	private File getDefaultDestination(File dir) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss"); //$NON-NLS-1$

		Timestamp now = new Timestamp(System.currentTimeMillis());

		File destination = new File(dir, sdf.format(now) + "_threadDump.txt");

		return destination;
	}
	
	
	
	public SimpleDateFormat getTimeFormat() {
		return timeFormat;
	}
	
	
	public void setTimeFormat(SimpleDateFormat timeFormat) {
		this.timeFormat = timeFormat;
	}
	

	private String getThreadNameList(long[] ids) {
		if (ids == null || ids.length == 0) {
			return "-"; //$NON-NLS-1$
		} else {
			List<String> names = new LinkedList<String>();

			for (long id : ids) {
				names.add(String.valueOf(id));
			}

			return CollectionUtilities.toString(names, ", "); //$NON-NLS-1$
		}
	}

}
