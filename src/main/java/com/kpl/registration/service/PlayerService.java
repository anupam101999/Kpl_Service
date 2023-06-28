package com.kpl.registration.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.kpl.registration.dto.GenericVO;
import com.kpl.registration.dto.PlayerRequetVO;
import com.kpl.registration.dto.RegistrationResponse;

public interface PlayerService{
	GenericVO savePlayerInfo(PlayerRequetVO playerRequetVO, byte[] imageData, byte[] docData)
			throws IOException ;

	RegistrationResponse getRegistrationStatus(String searchParam);

	void generatePdfByClassification(HttpServletResponse response, String generue) throws DocumentException, IOException;

	void generueSpecificPlayerPdfForCommitte(HttpServletResponse response) throws DocumentException, IOException;

	void generateFinalPlayerPdf(HttpServletResponse response, String generue) throws DocumentException, IOException;
}