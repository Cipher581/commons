package art.cipher581.commons.util;


import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.management.MBeanServer;

import com.sun.management.HotSpotDiagnosticMXBean;


public class HeapDumper {

	private static final String HOTSPOT_BEAN_NAME = "com.sun.management:type=HotSpotDiagnostic"; //$NON-NLS-1$


	public File createHeapDump(File dest) throws IOException {
		return createHeapDump(dest, true);
	}


	public File createHeapDump(File dest, boolean live) throws IOException {
		if (dest == null) {
			dest = getDefaultDestination(new File("."));
		} else if (dest.isDirectory()) {
			dest = getDefaultDestination(dest);
		}

		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		HotSpotDiagnosticMXBean bean = ManagementFactory.newPlatformMXBeanProxy(server, HOTSPOT_BEAN_NAME, HotSpotDiagnosticMXBean.class);

		bean.dumpHeap(dest.getAbsolutePath(), live);

		return dest;
	}


	private File getDefaultDestination(File dir) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss"); //$NON-NLS-1$

		Timestamp now = new Timestamp(System.currentTimeMillis());

		File destination = new File(dir, sdf.format(now) + "_heapDump");

		return destination;
	}
}
