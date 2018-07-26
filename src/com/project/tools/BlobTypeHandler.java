package com.project.tools;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

@MappedJdbcTypes(JdbcType.BLOB)
public class BlobTypeHandler extends BaseTypeHandler<File> {
	
	/**
	 * 从数据库中查询 File 使用
	 */
	@Override
	public File getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
////		System.out.println("getNullableResult");
////		System.out.println(rs.getMetaData().getColumnName(2));
//		FileHandler fileHandler = new FileHandler();
////		String filePath = PropertiesHandler.paramFilePath + rs.getMetaData().getColumnName(2) + ".dat";
//		String filePath = PropertiesHandler.paramFilePath + rs.getString(2) + ".dat";
////		System.out.println("********" + rs.getString(2));
//		fileHandler.buildFile(filePath);
		File file = new File("");
//		
//		Blob blob = rs.getBlob(columnName);
//		if (null != blob) {
//			OutputStream outputStream = null;
//			InputStream is = null;
//			try {
//				outputStream = new FileOutputStream(file);
//				is = new ByteArrayInputStream(blob.getBytes(1, (int) blob.length()));
//				byte[] buff = new byte[1024];
//				int len = 0;
//				while ((len = is.read(buff)) != -1) {
//					outputStream.write(buff, 0, len);
//				}
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					outputStream.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return file;
	}

	@Override
	public File getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		File file = new File("d:/result.dat");
//		FileHandler fileHandler = new FileHandler();
//		String filePath = PropertiesHandler.paramFilePath + rs.getMetaData().getColumnName(2) + ".dat";
//		fileHandler.buildFile(filePath);
//		File file = new File(filePath);
//		
//		if (rs.next()) {
//			InputStream inputStream = rs.getAsciiStream(columnIndex);
//			OutputStream outputStream = null;
//			try {
//				outputStream = new BufferedOutputStream(new FileOutputStream(file));
//				byte[] bt = new byte[1024];
//				for (int i = 0; (i = inputStream.read(bt)) > 0;) {
//					outputStream.write(bt, 0, i);
//				}
//				outputStream.flush();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					outputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return file;
	}

	@Override
	public File getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return null;
	}

	/**
	 *	向数据库中存储 File 使用
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			File file, JdbcType jdbcType) throws SQLException {
//		System.out.println("setNonNullParameter");
////		System.out.println("File: " + file);
//		FileInputStream fis = null;
//		ByteArrayOutputStream bos = null;
//		ByteArrayInputStream bis = null;
//		
//		byte[] buffer = null;
//		try {
//			fis = new FileInputStream(file);
//			bos = new ByteArrayOutputStream();
//
//			byte[] b = new byte[1024];
//            int len;
//            while ((len = fis.read(b)) != -1) {
//                bos.write(b, 0, len);
//            }
//            buffer = bos.toByteArray();
//            
//            bis = new ByteArrayInputStream(buffer);
//            ps.setBinaryStream(i, bis, buffer.length);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				bis.close();
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			try {
//				bos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				fis.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

}
