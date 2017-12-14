package com.mitrais.rms.dao;

import com.mitrais.rms.model.MasterData;

import java.util.List;

/**
 * Provides interface specific to {@link User} data
 */
public interface MasterDataDao
{
    /**
     * Find {@link MasterData} by its code type
     * @param codeType code type
     * @return list of standard code
     */
    List<MasterData> findByCodeType(String codeType);
}