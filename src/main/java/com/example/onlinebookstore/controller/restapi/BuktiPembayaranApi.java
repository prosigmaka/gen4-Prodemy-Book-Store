package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.BuktiPembayaranDto;
import com.example.onlinebookstore.model.dto.CheckoutOrderDto;
import com.example.onlinebookstore.model.entity.*;
import com.example.onlinebookstore.repository.BuktiPembayaranRepository;
import com.example.onlinebookstore.repository.CheckoutItemRepository;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.BuktiPembayaranService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bp")
public class BuktiPembayaranApi {
    @Autowired
    public KeranjangRepository keranjangRepository;
    @Autowired
    public CheckoutOrderRepository checkoutOrderRepository;
    @Autowired
    public CheckoutItemRepository checkoutItemRepository;
    @Autowired
    public BuktiPembayaranRepository buktiPembayaranRepository;
    @Autowired
    public BuktiPembayaranService buktiPembayaranService;
    @Autowired
    public ModelMapper modelMapper;


    @GetMapping("/payment/{idCO}")
    public BuktiPembayaranDto paymentCheckoutOrder(@PathVariable Integer idCO) {
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findById(idCO).get();
        BuktiPembayaran buktiPembayaran = buktiPembayaranRepository.findByIdCo(idCO);
        BuktiPembayaranDto buktiPembayaranDto = new BuktiPembayaranDto();

        if (buktiPembayaran == null){
//            buktiPembayaran.setIdCo(checkoutOrder.getId());
            // jika tidak pakai model mapper maka perlu setter getter satu satu
            buktiPembayaranDto.setIdCo(checkoutOrder.getId());
            buktiPembayaranDto.setTanggalOrder(checkoutOrder.getTanggalOrder());
            buktiPembayaranDto.setBatasTanggalPembayaran(checkoutOrder.getBatasTanggalPembayaran());
            buktiPembayaranDto.setTipePembayaran(checkoutOrder.getTipePembayaran());
            buktiPembayaranDto.setTotalHargalCi(checkoutOrder.getTotalHargalCi());
        }else {
            modelMapper.map(buktiPembayaran,BuktiPembayaranDto.class);
            buktiPembayaranDto.setIdCo(buktiPembayaran.getIdCo());
            buktiPembayaranDto.setTanggalOrder(buktiPembayaran.getCheckoutOrder().getTanggalOrder());
            buktiPembayaranDto.setBatasTanggalPembayaran(buktiPembayaran.getCheckoutOrder().getBatasTanggalPembayaran());
            buktiPembayaranDto.setTipePembayaran(buktiPembayaran.getCheckoutOrder().getTipePembayaran());
            buktiPembayaranDto.setTotalHargalCi(buktiPembayaran.getCheckoutOrder().getTotalHargalCi());
        }


        return buktiPembayaranDto;
    }


    @PostMapping("/save-pop")
    public BuktiPembayaranDto saveOrEditBuktiPembayaran(@RequestBody BuktiPembayaranDto buktiPembayaranDto) {
        BuktiPembayaran buktiPembayaran = modelMapper.map(buktiPembayaranDto, BuktiPembayaran.class);
        buktiPembayaran.setIdCo(buktiPembayaranDto.getIdCo());
        BuktiPembayaran popNew = buktiPembayaranService.saveBuktiPembayaranService(buktiPembayaran);
        BuktiPembayaranDto popDtoNew = mapToDtoBP(popNew);

        return popDtoNew;
    }

    private BuktiPembayaranDto mapToDtoBP(BuktiPembayaran buktiPembayaran) {
        BuktiPembayaranDto buktiPembayaranDto = modelMapper.map(buktiPembayaran, BuktiPembayaranDto.class);

        buktiPembayaranDto.setIdCo(buktiPembayaran.getCheckoutOrder().getId());
        buktiPembayaranDto.setTanggalOrder(buktiPembayaran.getCheckoutOrder().getTanggalOrder());
        buktiPembayaranDto.setTotalHargalCi(buktiPembayaran.getCheckoutOrder().getTotalHargalCi());
        buktiPembayaranDto.setBatasTanggalPembayaran(buktiPembayaran.getCheckoutOrder().getBatasTanggalPembayaran());
        buktiPembayaranDto.setTipePembayaran(buktiPembayaran.getCheckoutOrder().getTipePembayaran());

        buktiPembayaranDto.setId(buktiPembayaran.getId());

        return buktiPembayaranDto;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/save-bp")
    public BuktiPembayaranDto saveOrEditBP(@RequestPart(value="data", required = true) BuktiPembayaranDto buktiPembayaranDto, @RequestPart(value="file", required = true) MultipartFile file) throws Exception {
        BuktiPembayaran buktiPembayaran = modelMapper.map(buktiPembayaranDto, BuktiPembayaran.class);

        String userFolderPath = "F:/JAVA/Mini Project/image-pop-prodemybookstore";
        Path path = Paths.get(userFolderPath);
        Path filePath = path.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        buktiPembayaran.setGambarBuktiPembayaran(file.getOriginalFilename());

        buktiPembayaran.setIdCo(buktiPembayaranDto.getIdCo());
        BuktiPembayaran popNew = buktiPembayaranService.saveBuktiPembayaranService(buktiPembayaran);
        BuktiPembayaranDto popDtoNew = mapToDtoBP(popNew);

        return popDtoNew;
    }

    @GetMapping("/getImage/{idBP}")
    public String getImagePOP(@PathVariable Integer idBP) throws IOException {
        BuktiPembayaran buktiPembayaran = buktiPembayaranRepository.findByIdCo(idBP);
        String userFolderPath = "F:/JAVA/Mini Project/image-pop-prodemybookstore";
        String pathFile = userFolderPath + buktiPembayaran.getGambarBuktiPembayaran();
        Path paths = Paths.get(pathFile);
        byte[] filePhoto = Files.readAllBytes(paths);
        String encodedFile = Base64.getEncoder().encodeToString(filePhoto);
        return encodedFile;
    }

    @GetMapping("/all-buktipembayaran")
    public List<BuktiPembayaranDto> getBuktiPembayaranList() {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<BuktiPembayaran> buktiPembayaranList = buktiPembayaranRepository.findAll();
        List<BuktiPembayaranDto> buktiPembayaranDtos =
                buktiPembayaranList.stream()
                        .map(bp -> mapToDtoBP(bp))
                        .collect(Collectors.toList());
        return buktiPembayaranDtos;
    }

    @GetMapping("/validate/{idBP}")
    public CheckoutOrderDto validateCheckoutOrder(@PathVariable Integer idBP) {
        BuktiPembayaran buktiPembayaran = buktiPembayaranRepository.findById(idBP).get();
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findById(buktiPembayaran.getIdCo()).get();
        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrder,CheckoutOrderDto.class);
        checkoutOrderDto.setIdCostumer(checkoutOrder.getIdCostumer());
        checkoutOrderDto.setId(checkoutOrder.getId());
        return checkoutOrderDto;
    }

    @PostMapping("/change-status-berhasil/{idBP}")
    public CheckoutOrder changeStatusBerhasil(@PathVariable Integer idBP/*@RequestBody CheckoutOrderDto checkoutOrderDto*/){
        BuktiPembayaran bpConfirmed = buktiPembayaranRepository.findById(idBP).get();
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findById(bpConfirmed.getIdCo()).get();
        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrder,CheckoutOrderDto.class);
        checkoutOrderDto.setIdCostumer(checkoutOrder.getIdCostumer());
        checkoutOrderDto.setId(checkoutOrder.getId());

        CheckoutOrder checkoutOrderNew = modelMapper.map(checkoutOrderDto,CheckoutOrder.class);
        checkoutOrderNew.setStatusPesanan(PesananStatus.DIPROSES);
        checkoutOrderNew.setIdCostumer(checkoutOrderDto.getIdCostumer());
        checkoutOrderNew.setId(checkoutOrderDto.getId());

        List<CheckoutItem> checkoutItemList =checkoutItemRepository.findAllByIdOrder(checkoutOrderNew.getId());
        for (int i = 0; i<checkoutItemList.size(); i++){
            CheckoutItem checkoutItemId = checkoutItemList.get(i);
            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItemId.getIdKeranjang());
            Keranjang k = keranjang.get();
            k.setStatusKeranjang(ItemStatus.PAID);
            keranjangRepository.save(k);
        }

        return checkoutOrderRepository.save(checkoutOrderNew);
    }

    @PostMapping("/change-status-gagal/{idBP}")
    public CheckoutOrder changeStatusGagal(@PathVariable Integer idBP/*@RequestBody CheckoutOrderDto checkoutOrderDto*/){
        BuktiPembayaran bpCanceled = buktiPembayaranRepository.findById(idBP).get();
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findById(bpCanceled.getIdCo()).get();
        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrder,CheckoutOrderDto.class);
        checkoutOrderDto.setIdCostumer(checkoutOrder.getIdCostumer());
        checkoutOrderDto.setId(checkoutOrder.getId());

        CheckoutOrder checkoutOrderNew = modelMapper.map(checkoutOrderDto,CheckoutOrder.class);
        checkoutOrderNew.setStatusPesanan(PesananStatus.DIBATALKAN);
        checkoutOrderNew.setIdCostumer(checkoutOrderDto.getIdCostumer());
        checkoutOrderNew.setId(checkoutOrderDto.getId());

        List<CheckoutItem> checkoutItemList =checkoutItemRepository.findAllByIdOrder(checkoutOrderNew.getId());
        for (int i = 0; i<checkoutItemList.size(); i++){
            CheckoutItem checkoutItemId = checkoutItemList.get(i);
            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItemId.getIdKeranjang());
            Keranjang k = keranjang.get();
            k.setStatusKeranjang(ItemStatus.CANCELED);
            keranjangRepository.save(k);
        }

        return checkoutOrderRepository.save(checkoutOrderNew);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        buktiPembayaranRepository.deleteById(id);
    }

}