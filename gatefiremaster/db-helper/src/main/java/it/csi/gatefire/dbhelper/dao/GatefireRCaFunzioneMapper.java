/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import it.csi.gatefire.dbhelper.model.GatefireRCaFunzione;
import it.csi.gatefire.dbhelper.model.GatefireRCaFunzioneExample;

public interface GatefireRCaFunzioneMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    long countByExample(GatefireRCaFunzioneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    int deleteByExample(GatefireRCaFunzioneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    int insert(GatefireRCaFunzione record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    int insertSelective(GatefireRCaFunzione record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    List<GatefireRCaFunzione> selectByExample(GatefireRCaFunzioneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    GatefireRCaFunzione selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    int updateByExampleSelective(@Param("record") GatefireRCaFunzione record, @Param("example") GatefireRCaFunzioneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    int updateByExample(@Param("record") GatefireRCaFunzione record, @Param("example") GatefireRCaFunzioneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    int updateByPrimaryKeySelective(GatefireRCaFunzione record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gatefire.gatefire_r_ca_funzione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    int updateByPrimaryKey(GatefireRCaFunzione record);
}