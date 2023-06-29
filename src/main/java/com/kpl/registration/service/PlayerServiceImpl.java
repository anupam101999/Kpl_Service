package com.kpl.registration.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.kpl.registration.dto.GenericVO;
import com.kpl.registration.dto.PlayerRequetVO;
import com.kpl.registration.dto.RegistrationResponse;
import com.kpl.registration.entity.PlayerInfo;
import com.kpl.registration.repository.ImageRepo;
import com.kpl.registration.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	ImageRepo imageRepo;

	@Override
	public GenericVO savePlayerInfo(PlayerRequetVO playerRequetVO, byte[] imageData, byte[] docData)
			throws IOException {
		GenericVO genericVO = new GenericVO();
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.setAadharNo(playerRequetVO.getAadharNo());

		playerInfo.setEmailId(playerRequetVO.getEmailId());
		playerInfo.setGenerue(playerRequetVO.getGenerue());
		playerInfo.setImage(imageData);
		playerInfo.setDocImage(docData);
		playerInfo.setPhNo(playerRequetVO.getPhNo());
		playerInfo.setPinCode(playerRequetVO.getPinCode());
		playerInfo.setPlayerAddress(playerRequetVO.getPlayerAddress());
		playerInfo.setPlayerFirstName(playerRequetVO.getPlayerFirstName());
		playerInfo.setPlayerLastName(playerRequetVO.getPlayerLastName());
		playerInfo.setRegistrationTime(LocalDateTime.now());
		playerInfo.setGenerue(playerRequetVO.getGenerue());
		playerInfo.setDateOfBirth(playerRequetVO.getDob());
		playerInfo.setPassword(playerRequetVO.getPassword());
		playerInfo.setLocation(playerRequetVO.getLocation());
		playerRepository.save(playerInfo);
		genericVO.setResponse("You have been successfully Registered");
		return genericVO;
	}

	@Override
	public RegistrationResponse getRegistrationStatus(String id, String password) {
		var playerExistance = playerRepository.findByMailOrPhNumberandpassword(id, password);
		if (playerExistance != null) {
			if (playerExistance.getPaymentValidation() != null) {
				playerExistance.setPaymentValidation("Completed");
			} else {
				playerExistance.setPaymentValidation("Pending Stage");
			}
			return modelMapper.map(playerExistance, RegistrationResponse.class);

		}
		RegistrationResponse registrationResponse = new RegistrationResponse();
		registrationResponse.setPlayerFirstName("No Record Found");
		registrationResponse.setPlayerLastName("No Record Found");
		return registrationResponse;
	}

	public Image getImageData(String name) throws BadElementException, MalformedURLException, IOException {

		byte[] imageData;

		imageData = imageRepo.findByImageName(name);

		Image imageDataEn = com.itextpdf.text.Image.getInstance(imageData);
		imageDataEn.setAlignment(Element.ALIGN_CENTER);
		imageDataEn.scalePercent(70);
		imageDataEn.setBorderWidth(1f);
		imageDataEn.scaleToFit(50, 50);
		imageDataEn.setAlignment(Element.ALIGN_LEFT);
		imageDataEn.setBorder(Rectangle.NO_BORDER);
		return imageDataEn;
	}

	public Image getPlayerSpecificImageData(Long registrationId)
			throws BadElementException, MalformedURLException, IOException {

		byte[] imageData;

		imageData = playerRepository.findByregistrationId(registrationId);

		Image imageDataEn = com.itextpdf.text.Image.getInstance(imageData);
		imageDataEn.setAlignment(Element.ALIGN_CENTER);
		imageDataEn.scalePercent(90);
		imageDataEn.scaleToFit(90, 90);
		imageDataEn.setAlignment(Element.ALIGN_LEFT);
		imageDataEn.setBorder(Rectangle.NO_BORDER);
		return imageDataEn;
	}

	@Override
	public void generatePdfByClassification(HttpServletResponse response, String generue)
			throws DocumentException, IOException {
		var allplayerInfo = playerRepository.findbyGenerue(generue);

		var yellowBold = "FORsmartNext-Bolds.otf";
		var font1 = FontFactory.getFont(yellowBold, 20, Font.BOLD, BaseColor.BLACK);
		var font2 = FontFactory.getFont(yellowBold, 15, Font.BOLD, BaseColor.BLACK);
		var tableFont = FontFactory.getFont(yellowBold, 7, Font.BOLD, BaseColor.BLACK);
		var tablesFont = FontFactory.getFont(yellowBold, 7, BaseColor.BLACK);
		var layout = new Rectangle(PageSize.A4);
		layout.setBackgroundColor(new BaseColor(255, 255, 255));
		layout.setBorderColor(BaseColor.WHITE);
		layout.setUseVariableBorders(true);
		layout.setBorderColorBottom(new BaseColor(220, 220, 0));
		layout.setBorderWidthBottom(20);
		layout.setBorderWidth(6);
		layout.setBorder(Rectangle.BOTTOM);

		var document = new Document(layout, 0, 0, 0, 20);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		var tables = new PdfPTable(3);
		tables.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tables.setWidthPercentage(100);
		tables.setSpacingAfter(-0.005f);
		float[] columnWidth = { 1f, 3f, 1f };
		tables.setWidths(columnWidth);

		var cel = new PdfPCell(new Phrase("\n" + "(" + generue + ")", font2));
		cel.setBackgroundColor(new BaseColor(220, 220, 0));
		cel.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel.setPaddingLeft(30f);
		cel.setPaddingTop(11f);
		cel.setVerticalAlignment(Element.ALIGN_BASELINE);
		cel.setBorderColor(new BaseColor(220, 220, 0));
		tables.addCell(cel);

		cel = new PdfPCell(new Phrase("\n" + "Kashipur Premier League - 5 ", font1));
		cel.setBackgroundColor(new BaseColor(220, 220, 0));
		cel.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel.setPaddingLeft(30f);
		cel.setPaddingBottom(15f);
		cel.setVerticalAlignment(Element.ALIGN_BASELINE);
		cel.setBorderColor(new BaseColor(220, 220, 0));
		tables.addCell(cel);

		cel = new PdfPCell(getImageData("logo"));
		cel.setBackgroundColor(new BaseColor(220, 220, 0));
		cel.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel.setPaddingLeft(10f);
		cel.setPaddingRight(10f);
		cel.setPaddingTop(10f);
		cel.setPaddingBottom(10f);
		cel.setVerticalAlignment(Element.ALIGN_BASELINE);
		cel.setBorderColor(new BaseColor(220, 220, 0));
		tables.addCell(cel);
		document.add(tables);

		LineSeparator ls = new LineSeparator();
		ls.setLineColor(BaseColor.BLACK);
		ls.setLineWidth(0.5f);
		ls.setPercentage(95);
		document.add(new Chunk(ls));

		document.add(Chunk.NEWLINE);

		PdfPTable ptable = new PdfPTable(6);
		ptable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		ptable.setWidthPercentage(95);
		ptable.setSpacingBefore(10f);
		ptable.setSpacingAfter(5f);
		var pcell = new PdfPCell(new Phrase("Sl No.", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Name", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(BaseColor.WHITE);
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Address", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Aadhar No", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Phone No", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Photo", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		for (int i = 0; i < allplayerInfo.size(); i++) {
			pcell = new PdfPCell(new Phrase(String.valueOf(i + 1), tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_CENTER);
			pcell.setPaddingBottom(7);
			pcell.setPaddingTop(20);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(
					allplayerInfo.get(i).getPlayerFirstName() + " " + allplayerInfo.get(i).getPlayerLastName(),
					tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getPlayerAddress(), tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase("XXXXXXXX" + allplayerInfo.get(i).getAadharNo().toString().substring(8)
					+ " " + "(" + allplayerInfo.get(i).getLocation() + ")", tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getPhNo().toString(), tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(getPlayerSpecificImageData(allplayerInfo.get(i).getRegistrationId()));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);
		}
		document.add(ptable);
		document.close();
	}

	@Override
	public void generueSpecificPlayerPdfForCommitte(HttpServletResponse response)
			throws DocumentException, IOException {
		var allplayerInfo = playerRepository.findAllPlayer();

		var yellowBold = "FORsmartNext-Bolds.otf";
		var font1 = FontFactory.getFont(yellowBold, 20, Font.BOLD, BaseColor.BLACK);
		var font2 = FontFactory.getFont(yellowBold, 15, Font.BOLD, BaseColor.BLACK);
		var tableFont = FontFactory.getFont(yellowBold, 7, Font.BOLD, BaseColor.BLACK);
		var tablesFont = FontFactory.getFont(yellowBold, 7, BaseColor.BLACK);
		var layout = new Rectangle(PageSize.A4);
		layout.setBackgroundColor(new BaseColor(255, 255, 255));
		layout.setBorderColor(BaseColor.WHITE);
		layout.setUseVariableBorders(true);
		layout.setBorderColorBottom(new BaseColor(220, 220, 0));
		layout.setBorderWidthBottom(20);
		layout.setBorderWidth(6);
		layout.setBorder(Rectangle.BOTTOM);

		var document = new Document(layout, 0, 0, 0, 20);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		var tables = new PdfPTable(3);
		tables.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tables.setWidthPercentage(100);
		tables.setSpacingAfter(-0.005f);
		float[] columnWidth = { 1f, 3f, 1f };
		tables.setWidths(columnWidth);

		var cel = new PdfPCell(new Phrase("\n" + "(" + "All Player" + ")", font2));
		cel.setBackgroundColor(new BaseColor(220, 220, 0));
		cel.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel.setPaddingLeft(30f);
		cel.setPaddingTop(11f);
		cel.setVerticalAlignment(Element.ALIGN_BASELINE);
		cel.setBorderColor(new BaseColor(220, 220, 0));
		tables.addCell(cel);

		cel = new PdfPCell(new Phrase("\n" + "Kashipur Premier League - 5 ", font1));
		cel.setBackgroundColor(new BaseColor(220, 220, 0));
		cel.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel.setPaddingLeft(30f);
		cel.setPaddingBottom(15f);
		cel.setVerticalAlignment(Element.ALIGN_BASELINE);
		cel.setBorderColor(new BaseColor(220, 220, 0));
		tables.addCell(cel);

		cel = new PdfPCell(getImageData("logo"));
		cel.setBackgroundColor(new BaseColor(220, 220, 0));
		cel.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel.setPaddingLeft(10f);
		cel.setPaddingRight(10f);
		cel.setPaddingTop(10f);
		cel.setPaddingBottom(10f);
		cel.setVerticalAlignment(Element.ALIGN_BASELINE);
		cel.setBorderColor(new BaseColor(220, 220, 0));
		tables.addCell(cel);
		document.add(tables);

		LineSeparator ls = new LineSeparator();
		ls.setLineColor(BaseColor.BLACK);
		ls.setLineWidth(0.5f);
		ls.setPercentage(95);
		document.add(new Chunk(ls));

		document.add(Chunk.NEWLINE);

		PdfPTable ptable = new PdfPTable(6);
		ptable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		ptable.setWidthPercentage(95);
		ptable.setSpacingBefore(10f);
		ptable.setSpacingAfter(5f);
		var pcell = new PdfPCell(new Phrase("Registration ID.", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Name", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(BaseColor.WHITE);
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Address", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Aadhar No", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Phone No", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Photo", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		for (int i = 0; i < allplayerInfo.size(); i++) {
			pcell = new PdfPCell(new Phrase(String.valueOf(allplayerInfo.get(i).getRegistrationId()), tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_CENTER);
			pcell.setPaddingBottom(7);
			pcell.setPaddingTop(20);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(
					allplayerInfo.get(i).getPlayerFirstName() + " " + allplayerInfo.get(i).getPlayerLastName(),
					tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getPlayerAddress(), tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getAadharNo().toString() + " " + "("
					+ allplayerInfo.get(i).getLocation() + ")", tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getPhNo().toString(), tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(getPlayerSpecificImageData(allplayerInfo.get(i).getRegistrationId()));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);
		}
		document.add(ptable);
		document.close();
	}

	@Override
	public void generateFinalPlayerPdf(HttpServletResponse response, String generue)
			throws DocumentException, IOException {
		var allplayerInfo = playerRepository.findbyGenerue(generue);

		var yellowBold = "FORsmartNext-Bolds.otf";
		var font1 = FontFactory.getFont(yellowBold, 20, Font.BOLD, BaseColor.BLACK);
		var font2 = FontFactory.getFont(yellowBold, 15, Font.BOLD, BaseColor.BLACK);
		var tableFont = FontFactory.getFont(yellowBold, 7, Font.BOLD, BaseColor.BLACK);
		var tablesFont = FontFactory.getFont(yellowBold, 7, BaseColor.BLACK);
		var layout = new Rectangle(PageSize.A4);
		layout.setBackgroundColor(new BaseColor(255, 255, 255));
		layout.setBorderColor(BaseColor.WHITE);
		layout.setUseVariableBorders(true);
		layout.setBorderColorBottom(new BaseColor(220, 220, 0));
		layout.setBorderWidthBottom(20);
		layout.setBorderWidth(6);
		layout.setBorder(Rectangle.BOTTOM);

		var document = new Document(layout, 0, 0, 0, 20);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		var tables = new PdfPTable(3);
		tables.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tables.setWidthPercentage(100);
		tables.setSpacingAfter(-0.005f);
		float[] columnWidth = { 1f, 3f, 1f };
		tables.setWidths(columnWidth);

		var cel = new PdfPCell(new Phrase("\n" + "(" + generue + ")", font2));
		cel.setBackgroundColor(new BaseColor(220, 220, 0));
		cel.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel.setPaddingLeft(30f);
		cel.setPaddingTop(11f);
		cel.setVerticalAlignment(Element.ALIGN_BASELINE);
		cel.setBorderColor(new BaseColor(220, 220, 0));
		tables.addCell(cel);

		cel = new PdfPCell(new Phrase("\n" + "Kashipur Premier League - 5 ", font1));
		cel.setBackgroundColor(new BaseColor(220, 220, 0));
		cel.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel.setPaddingLeft(30f);
		cel.setPaddingBottom(15f);
		cel.setVerticalAlignment(Element.ALIGN_BASELINE);
		cel.setBorderColor(new BaseColor(220, 220, 0));
		tables.addCell(cel);

		cel = new PdfPCell(getImageData("logo"));
		cel.setBackgroundColor(new BaseColor(220, 220, 0));
		cel.setHorizontalAlignment(Element.ALIGN_LEFT);
		cel.setPaddingLeft(10f);
		cel.setPaddingRight(10f);
		cel.setPaddingTop(10f);
		cel.setPaddingBottom(10f);
		cel.setVerticalAlignment(Element.ALIGN_BASELINE);
		cel.setBorderColor(new BaseColor(220, 220, 0));
		tables.addCell(cel);
		document.add(tables);

		LineSeparator ls = new LineSeparator();
		ls.setLineColor(BaseColor.BLACK);
		ls.setLineWidth(0.5f);
		ls.setPercentage(95);
		document.add(new Chunk(ls));

		document.add(Chunk.NEWLINE);

		PdfPTable ptable = new PdfPTable(6);
		ptable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		ptable.setWidthPercentage(95);
		ptable.setSpacingBefore(10f);
		ptable.setSpacingAfter(5f);
		var pcell = new PdfPCell(new Phrase("Sl No.", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Name", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(BaseColor.WHITE);
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Aadhar No", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Phone No", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Photo", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		pcell = new PdfPCell(new Phrase("Sold Amount", tableFont));
		pcell.setBackgroundColor(new BaseColor(220, 220, 0));
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pcell.setVerticalAlignment(Element.ALIGN_CENTER);
		pcell.setPaddingLeft(10f);
		pcell.setBorderColor(new BaseColor(220, 220, 0));
		ptable.addCell(pcell);

		for (int i = 0; i < allplayerInfo.size(); i++) {
			pcell = new PdfPCell(new Phrase(String.valueOf(i + 1), tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_CENTER);
			pcell.setPaddingBottom(7);
			pcell.setPaddingTop(20);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(
					allplayerInfo.get(i).getPlayerFirstName() + " " + allplayerInfo.get(i).getPlayerLastName(),
					tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getAadharNo().toString() + " " + "("
					+ allplayerInfo.get(i).getLocation() + ")", tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getPhNo().toString(), tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(getPlayerSpecificImageData(allplayerInfo.get(i).getRegistrationId()));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

			pcell = new PdfPCell(new Phrase("", tablesFont));
			pcell.setBackgroundColor(new BaseColor(230, 230, 230));
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			pcell.setBorderColor(BaseColor.WHITE);
			ptable.addCell(pcell);

		}
		document.add(ptable);
		document.close();
	}
}