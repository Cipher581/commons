package art.cipher581.commons.util;


import java.io.Closeable;
import java.io.Flushable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Safely {

	public static void close(Closeable closeable) {
		// System.out.println("close");
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Throwable ex) {
				ex.printStackTrace();
				// TODO
			}
		}
	}
        
        
        public static void close(Connection con) {
		// System.out.println("close");
		if (con != null) {
			try {
				con.close();
			} catch (Throwable ex) {
				ex.printStackTrace();
				// TODO
			}
		}
	}
        
        
        public static void close(ResultSet rs) {
		// System.out.println("close");
		if (rs != null) {
			try {
				rs.close();
			} catch (Throwable ex) {
				ex.printStackTrace();
				// TODO
			}
		}
	}
        
        
        public static void close(PreparedStatement ps) {
		// System.out.println("close");
		if (ps != null) {
			try {
				ps.close();
			} catch (Throwable ex) {
				ex.printStackTrace();
				// TODO
			}
		}
	}


		public static void flush(Flushable f) {
			if (f != null) {
				try {
					f.flush();
				} catch (Throwable ex) {
					// ignore
				}
			}
		}

}
