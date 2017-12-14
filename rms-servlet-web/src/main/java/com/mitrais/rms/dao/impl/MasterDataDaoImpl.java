package com.mitrais.rms.dao.impl;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.MasterDataDao;
import com.mitrais.rms.model.MasterData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MasterDataDaoImpl implements MasterDataDao {
	@Override
	public List<MasterData> findByCodeType(String codeType) {
		List<MasterData> MasterDataList = new ArrayList<MasterData>();
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM master_data WHERE code_type=?");
			stmt.setString(1, codeType);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				MasterData MasterData = MasterDataMappingFromResultSet(rs);
				MasterDataList.add(MasterData);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return MasterDataList;
	}

	private static class SingletonHelper {
		private static final MasterDataDaoImpl INSTANCE = new MasterDataDaoImpl();
	}

	public static MasterDataDao getInstance() {
		return SingletonHelper.INSTANCE;
	}

	private static MasterData MasterDataMappingFromResultSet(ResultSet rs) throws SQLException {
		return new MasterData(rs.getString("code_type"), rs.getString("code_id"), rs.getString("description"));
	}
}
