package com.baeldung.barcodes;

import com.baeldung.barcodes.generators.BarbecueBarcodeGenerator;
import com.baeldung.barcodes.generators.Barcode4jBarcodeGenerator;
import com.baeldung.barcodes.generators.QRGenBarcodeGenerator;
import com.baeldung.barcodes.generators.ZxingBarcodeGenerator;
import com.baeldung.barcodes.generators.OkapiBarcodeGenerator;
import com.baeldung.barcodes.generators.ZxingBarcodeGeneratorWithText;
import com.baeldung.barcodes.generators.QRCodegenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/barcodes")
public class BarcodesController {

    //Barbecue library

    @GetMapping(value = "/barbecue/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueUPCABarcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(BarbecueBarcodeGenerator.generateUPCABarcodeImage(barcode));
    }

    @GetMapping(value = "/barbecue/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(BarbecueBarcodeGenerator.generateEAN13BarcodeImage(barcode));
    }

    @PostMapping(value = "/barbecue/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueCode128Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(BarbecueBarcodeGenerator.generateCode128BarcodeImage(barcode));
    }

    @PostMapping(value = "/barbecue/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecuePDF417Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(BarbecueBarcodeGenerator.generatePDF417BarcodeImage(barcode));
    }

    //Barcode4j library

    @GetMapping(value = "/barcode4j/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode4jUPCABarcode(@PathVariable("barcode") String barcode) {
        return okResponse(Barcode4jBarcodeGenerator.generateUPCABarcodeImage(barcode));
    }

    @GetMapping(value = "/barcode4j/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode4jEAN13Barcode(@PathVariable("barcode") String barcode) {
        return okResponse(Barcode4jBarcodeGenerator.generateEAN13BarcodeImage(barcode));
    }

    @PostMapping(value = "/barcode4j/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode4jCode128Barcode(@RequestBody String barcode) {
        return okResponse(Barcode4jBarcodeGenerator.generateCode128BarcodeImage(barcode));
    }

    @PostMapping(value = "/barcode4j/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode4jPDF417Barcode(@RequestBody String barcode) {
        return okResponse(Barcode4jBarcodeGenerator.generatePDF417BarcodeImage(barcode));
    }

    //Zxing library

    @GetMapping(value = "/zxing/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingUPCABarcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateUPCABarcodeImage(barcode));
    }

    @GetMapping(value = "/zxing/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingEAN13Barcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateEAN13BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingCode128Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateCode128BarcodeImage(barcode));
    }

    @GetMapping(value = "/zxing/qrcode/text", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingCodeQRcodeText(@RequestParam("barcode") String barcode, @RequestParam("toptext") String toptext, @RequestParam("bottomtext") String bottomtext) throws Exception {
        return okResponse(ZxingBarcodeGeneratorWithText.createQRwithText(barcode, toptext, bottomtext));
    }

    @PostMapping(value = "/zxing/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingPDF417Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generatePDF417BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateQRCodeImage(barcode));
    }

    //QRGen

    @PostMapping(value = "/qrgen/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> qrgenQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(QRGenBarcodeGenerator.generateQRCodeImage(barcode));
    }

    @GetMapping(value = "/okapi/2d/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> okapiBarcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(OkapiBarcodeGenerator.generateOkapiBarcode(barcode));
    }

    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    //QRCodegen
    @PostMapping(value = "/qrcodegen/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> qrcodegenQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(QRCodegenGenerator.generateQrcode(barcode));
    }
}
