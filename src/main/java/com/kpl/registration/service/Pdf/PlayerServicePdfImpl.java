package com.kpl.registration.service.Pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.kpl.registration.repository.PlayerRepo2024;
import com.kpl.registration.repository.PlayerRepository;
import com.kpl.registration.util.PdfUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@Service
public class PlayerServicePdfImpl implements PlayerServicePdf {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerRepo2024 playerRepo2024;
    PdfUtil _pdfUtil=new PdfUtil();
    @Override
    public void generatePdfByClassification(HttpServletResponse response, String generue)
            throws DocumentException, IOException {

        String category = generue.split(",")[1];
        String location = generue.split(",")[0];
        var allplayerInfo = playerRepository.findbyCategoryLocation(category, location);

        var yellowBold = "FORsmartNext-Bolds.otf";
        var font1 = FontFactory.getFont(yellowBold, 20, Font.BOLD, BaseColor.BLACK);
        var font2 = FontFactory.getFont(yellowBold, 15, Font.BOLD, BaseColor.BLACK);
        var tableFont = FontFactory.getFont(yellowBold, 8, Font.BOLD, BaseColor.BLACK);
        var tablesFont = FontFactory.getFont(yellowBold, 8, Font.BOLD, BaseColor.BLACK);
        var layout = new Rectangle(PageSize.A4);
        layout.setBackgroundColor(new BaseColor(255, 255, 255));
        layout.setBorderColor(BaseColor.WHITE);
        layout.setUseVariableBorders(true);
        layout.setBorderColorBottom(new BaseColor(255, 192, 203));
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
        float[] columnWidth = {1f, 3f, 1f};
        tables.setWidths(columnWidth);

        var cel = new PdfPCell(new Phrase("\n" + "(" + generue + ")", font2));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(30f);
        cel.setPaddingTop(11f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
        tables.addCell(cel);

        cel = new PdfPCell(new Phrase("\n" + "Kashipur Premier League - 6 ", font1));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(30f);
        cel.setPaddingBottom(15f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
        tables.addCell(cel);

        cel = new PdfPCell(_pdfUtil.getImageData("logo"));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(10f);
        cel.setPaddingRight(10f);
        cel.setPaddingTop(10f);
        cel.setPaddingBottom(10f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
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
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Name", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(BaseColor.WHITE);
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Address", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Category", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Phone No", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Photo", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        for (int i = 0; i < allplayerInfo.size(); i++) {

            pcell = new PdfPCell(new Phrase(String.valueOf(i + 1) + "          " + "(Reg ID : "
                    + allplayerInfo.get(i).getRegistrationId() + ")", tablesFont));
            pcell.setBackgroundColor(new BaseColor(230, 230, 230));
            pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pcell.setBorderColor(BaseColor.WHITE);
            ptable.addCell(pcell);
            var basePrice = 0L;
            basePrice = allplayerInfo.get(i).getBasePrice();

            pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getPlayerFirstName() + " "
                    + allplayerInfo.get(i).getPlayerLastName() + " (Base Price : " + basePrice + " )", tablesFont));
            pcell.setBackgroundColor(new BaseColor(230, 230, 230));
            pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pcell.setBorderColor(BaseColor.WHITE);
            ptable.addCell(pcell);

            pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getPlayerAddress(), tablesFont));
            pcell.setBackgroundColor(new BaseColor(230, 230, 230));
            pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pcell.setBorderColor(BaseColor.WHITE);
            ptable.addCell(pcell);

            pcell = new PdfPCell(
                    new Phrase(allplayerInfo.get(i).getGenerue() + " " + "(" + allplayerInfo.get(i).getLocation() + ")",
                            tablesFont));
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

            pcell = new PdfPCell(_pdfUtil.getPlayerSpecificImageData(allplayerInfo.get(i).getRegistrationId()));
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
        var allplayerInfo = playerRepo2024.findAllPlayer();

        var yellowBold = "FORsmartNext-Bolds.otf";
        var font1 = FontFactory.getFont(yellowBold, 20, Font.BOLD, BaseColor.BLACK);
        var font2 = FontFactory.getFont(yellowBold, 15, Font.BOLD, BaseColor.BLACK);
        var tableFont = FontFactory.getFont(yellowBold, 8, Font.BOLD, BaseColor.BLACK);
        var tablesFont = FontFactory.getFont(yellowBold, 8, Font.BOLD, BaseColor.BLACK);


        var layout = new Rectangle(PageSize.A4);
        layout.setBackgroundColor(new BaseColor(255, 255, 255));
        layout.setBorderColor(BaseColor.WHITE);
        layout.setUseVariableBorders(true);
        layout.setBorderColorBottom(new BaseColor(255, 192, 203));
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
        float[] columnWidth = {1f, 3f, 1f};
        tables.setWidths(columnWidth);

        var cel = new PdfPCell(new Phrase("\n" + "(" + "All Player" + ")", font2));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(30f);
        cel.setPaddingTop(11f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
        tables.addCell(cel);

        cel = new PdfPCell(new Phrase("\n" + "Kashipur Premier League - 6 ", font1));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(30f);
        cel.setPaddingBottom(15f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
        tables.addCell(cel);

        cel = new PdfPCell(_pdfUtil.getImageData("logo"));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(10f);
        cel.setPaddingRight(10f);
        cel.setPaddingTop(10f);
        cel.setPaddingBottom(10f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
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


        var pcell = new PdfPCell();

        boolean x = true;

        for (int i = 0; i < allplayerInfo.size(); i++) {
            if (x) {
                log.info(allplayerInfo.get(i).getRegId().toString());
                pcell = new PdfPCell(new Phrase(String.valueOf(i + 1) + "          " + "(Reg ID : "
                        + allplayerInfo.get(i).getRegId() + ")", tablesFont));
                pcell.setBackgroundColor(new BaseColor(230, 230, 230));
                pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pcell.setBorderColor(BaseColor.WHITE);
                ptable.addCell(pcell);

                var basePrice = 0L;
                if (allplayerInfo.get(i).getBasePrice() != null) {
                    basePrice = allplayerInfo.get(i).getBasePrice();
                }

                String playerName = allplayerInfo.get(i).getPlayerFirstName() + " "
                        + allplayerInfo.get(i).getPlayerLastName();
                String basePriceText = "Base Price : " + basePrice;

                Paragraph paragraph = new Paragraph();
                paragraph.setAlignment(Element.ALIGN_CENTER);
                paragraph.add(new Chunk(playerName, tablesFont));
                paragraph.add(Chunk.NEWLINE); // Add a new line
                paragraph.add(new Chunk(basePriceText, tablesFont));

                pcell.addElement(paragraph);

                pcell.setBackgroundColor(new BaseColor(230, 230, 230));
                pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pcell.setBorderColor(BaseColor.WHITE);
                ptable.addCell(pcell);

                pcell = new PdfPCell(getPlayerSpecificImageDataSeasonSix(allplayerInfo.get(i).getImage()));
                pcell.setBackgroundColor(new BaseColor(230, 230, 230));
                pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pcell.setBorderColor(BaseColor.WHITE);
                ptable.addCell(pcell);

                if (i % 2 != 0) {
                    i -= 2;
                    x = false;
                }
            } else {
                pcell = new PdfPCell(_pdfUtil.getImageData("logo"));
                pcell.setBackgroundColor(new BaseColor(230, 230, 230));
                pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pcell.setBorderColor(BaseColor.WHITE);
                ptable.addCell(pcell);


                pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getPlayerAddress(), tablesFont));
                pcell.setBackgroundColor(new BaseColor(230, 230, 230));
                pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pcell.setBorderColor(BaseColor.WHITE);
                ptable.addCell(pcell);


                pcell = new PdfPCell(new Phrase("(" + allplayerInfo.get(i).getPhNo().toString() +")"+ " " + allplayerInfo.get(i).getCategory() + " " + "(" + allplayerInfo.get(i).getPlayerLocationCategory() + ")",
                        tablesFont));
                pcell.setBackgroundColor(new BaseColor(230, 230, 230));
                pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pcell.setBorderColor(BaseColor.WHITE);
                ptable.addCell(pcell);


                if (i % 2 != 0) {
                    x = true;
                }

            }
        }

        document.add(ptable);
        document.close();
    }

    @Override
    public void generateFinalPlayerPdf(HttpServletResponse response, String generue)
            throws DocumentException, IOException {

        String category = generue.split(",")[1];
        String location = generue.split(",")[0];
        var allplayerInfo = playerRepository.findbyCategoryLocation(category, location);

        var yellowBold = "FORsmartNext-Bolds.otf";
        var font1 = FontFactory.getFont(yellowBold, 20, Font.BOLD, BaseColor.BLACK);
        var font2 = FontFactory.getFont(yellowBold, 15, Font.BOLD, BaseColor.BLACK);
        var tableFont = FontFactory.getFont(yellowBold, 8, Font.BOLD, BaseColor.BLACK);
        var tablesFont = FontFactory.getFont(yellowBold, 8, Font.BOLD, BaseColor.BLACK);
        var layout = new Rectangle(PageSize.A4);
        layout.setBackgroundColor(new BaseColor(255, 255, 255));
        layout.setBorderColor(BaseColor.WHITE);
        layout.setUseVariableBorders(true);
        layout.setBorderColorBottom(new BaseColor(255, 192, 203));
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
        float[] columnWidth = {1f, 3f, 1f};
        tables.setWidths(columnWidth);

        var cel = new PdfPCell(new Phrase("\n" + "(" + generue + ")", font2));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(30f);
        cel.setPaddingTop(11f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
        tables.addCell(cel);

        cel = new PdfPCell(new Phrase("\n" + "Kashipur Premier League - 6 ", font1));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(30f);
        cel.setPaddingBottom(15f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
        tables.addCell(cel);

        cel = new PdfPCell(_pdfUtil.getImageData("logo"));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(10f);
        cel.setPaddingRight(10f);
        cel.setPaddingTop(10f);
        cel.setPaddingBottom(10f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
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
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Name", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(BaseColor.WHITE);
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Phone No", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Photo", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Sold Team", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setPaddingLeft(10f);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Sold Amount", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setPaddingLeft(10f);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        for (int i = 0; i < allplayerInfo.size(); i++) {
            pcell = new PdfPCell(new Phrase(String.valueOf(i + 1) + "          " + "(Reg ID : "
                    + allplayerInfo.get(i).getRegistrationId() + ")", tablesFont));
            pcell.setBackgroundColor(new BaseColor(230, 230, 230));
            pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pcell.setBorderColor(BaseColor.WHITE);
            ptable.addCell(pcell);

            var basePrice = 0L;
            basePrice = allplayerInfo.get(i).getBasePrice();

            String playerName = allplayerInfo.get(i).getPlayerFirstName() + " "
                    + allplayerInfo.get(i).getPlayerLastName();
            String basePriceText = "Base Price : " + basePrice;

            Paragraph paragraph = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(new Chunk(playerName, tablesFont));
            paragraph.add(Chunk.NEWLINE); // Add a new line
            paragraph.add(new Chunk(basePriceText, tablesFont));

            pcell.addElement(paragraph);

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

            pcell = new PdfPCell(_pdfUtil.getPlayerSpecificImageData(allplayerInfo.get(i).getRegistrationId()));
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


    @Override
    public void generateTeamListPdf(HttpServletResponse response, String soldTeam)
            throws DocumentException, IOException {
        var allplayerInfo = playerRepo2024.findbyTeam(soldTeam);
        var yellowBold = "FORsmartNext-Bolds.otf";
        var font1 = FontFactory.getFont(yellowBold, 20, Font.BOLD, BaseColor.BLACK);
        var font2 = FontFactory.getFont(yellowBold, 15, Font.BOLD, BaseColor.BLACK);
        var tableFont = FontFactory.getFont(yellowBold, 8, Font.BOLD, BaseColor.BLACK);
        var tablesFont = FontFactory.getFont(yellowBold, 8, Font.BOLD, BaseColor.BLACK);
        var layout = new Rectangle(PageSize.A4);
        layout.setBackgroundColor(new BaseColor(255, 255, 255));
        layout.setBorderColor(BaseColor.WHITE);
        layout.setUseVariableBorders(true);
        layout.setBorderColorBottom(new BaseColor(255, 192, 203));
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
        float[] columnWidth = {1f, 3f, 1f};
        tables.setWidths(columnWidth);

        var cel = new PdfPCell(new Phrase("\n" + "(" + soldTeam + ")", font2));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(30f);
        cel.setPaddingTop(11f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
        tables.addCell(cel);

        cel = new PdfPCell(new Phrase("\n" + "Kashipur Premier League - 6 ", font1));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(30f);
        cel.setPaddingBottom(15f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
        tables.addCell(cel);

        cel = new PdfPCell(_pdfUtil.getImageData("logo"));
        cel.setBackgroundColor(new BaseColor(255, 192, 203));
        cel.setHorizontalAlignment(Element.ALIGN_LEFT);
        cel.setPaddingLeft(10f);
        cel.setPaddingRight(10f);
        cel.setPaddingTop(10f);
        cel.setPaddingBottom(10f);
        cel.setVerticalAlignment(Element.ALIGN_BASELINE);
        cel.setBorderColor(new BaseColor(255, 192, 203));
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
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Name", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(BaseColor.WHITE);
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Aadhar No", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Phone No", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Photo", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        pcell = new PdfPCell(new Phrase("Sold Amount", tableFont));
        pcell.setBackgroundColor(new BaseColor(255, 192, 203));
        pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pcell.setVerticalAlignment(Element.ALIGN_CENTER);
        pcell.setPaddingLeft(10f);
        pcell.setBorderColor(new BaseColor(255, 192, 203));
        ptable.addCell(pcell);

        for (int i = 0; i < allplayerInfo.size(); i++) {
            pcell = new PdfPCell(new Phrase(String.valueOf(i + 1) + "          " + "(Reg ID : "
                    + allplayerInfo.get(i).getRegId() + ")", tablesFont));
            pcell.setBackgroundColor(new BaseColor(230, 230, 230));
            pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pcell.setBorderColor(BaseColor.WHITE);
            ptable.addCell(pcell);

            pcell = new PdfPCell(new Phrase(
                    allplayerInfo.get(i).getPlayerFirstName() + " " + allplayerInfo.get(i).getPlayerLastName(),
                    tablesFont));
            pcell.setBackgroundColor(new BaseColor(230, 230, 230));
            pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pcell.setBorderColor(BaseColor.WHITE);
            ptable.addCell(pcell);

            var basePrice = 0L;
            if (allplayerInfo.get(i).getBasePrice() != null) {
                basePrice = allplayerInfo.get(i).getBasePrice();
            }

            String playerName = allplayerInfo.get(i).getPlayerFirstName() + " "
                    + allplayerInfo.get(i).getPlayerLastName();
            String basePriceText = "Base Price : " + basePrice;

            Paragraph paragraph = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(new Chunk(playerName, tablesFont));
            paragraph.add(Chunk.NEWLINE); // Add a new line
            paragraph.add(new Chunk(basePriceText, tablesFont));

            pcell.addElement(paragraph);

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

            pcell = new PdfPCell(getPlayerSpecificImageDataSeasonSix(allplayerInfo.get(i).getImage()));
            pcell.setBackgroundColor(new BaseColor(230, 230, 230));
            pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pcell.setBorderColor(BaseColor.WHITE);
            ptable.addCell(pcell);

            pcell = new PdfPCell(new Phrase(allplayerInfo.get(i).getSoldAmount().toString(), tablesFont));
            pcell.setBackgroundColor(new BaseColor(230, 230, 230));
            pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pcell.setBorderColor(BaseColor.WHITE);
            ptable.addCell(pcell);

        }
        document.add(ptable);
        document.close();
    }

    public Image getPlayerSpecificImageDataSeasonSix(byte[] imageData)
            throws BadElementException, MalformedURLException, IOException {

        Image imageDataEn = Image.getInstance(imageData);
        imageDataEn.setAlignment(Element.ALIGN_CENTER);
        imageDataEn.scalePercent(90);
        imageDataEn.scaleToFit(90, 90);
        imageDataEn.setAlignment(Element.ALIGN_LEFT);
        imageDataEn.setBorder(Rectangle.NO_BORDER);
        return imageDataEn;
    }

}
