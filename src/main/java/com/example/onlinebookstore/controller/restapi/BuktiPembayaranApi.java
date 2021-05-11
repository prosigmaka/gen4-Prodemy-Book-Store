package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.BuktiPembayaranDto;
import com.example.onlinebookstore.model.dto.CheckoutOrderDto;
import com.example.onlinebookstore.model.entity.*;
import com.example.onlinebookstore.repository.*;
import com.example.onlinebookstore.service.BuktiPembayaranService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bp")
public class BuktiPembayaranApi {
    @Autowired
    public KeranjangRepository keranjangRepository;
    @Autowired
    private BookRepository bookRepository;
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

//    @GetMapping("/{idPembayaran}")
//    public BuktiPembayaranDto getById(@PathVariable Integer id) {
//        BuktiPembayaran buktiPembayaran = buktiPembayaranRepository.findById(id).get();
//        BuktiPembayaranDto buktiPembayaranDto = modelMapper.map(buktiPembayaran,BuktiPembayaranDto.class);
//        return buktiPembayaranDto;
//    }

//    @GetMapping("/{idAja}")
//    public BuktiPembayaranDto getCheckoutOrder(@PathVariable Integer idCO) {
//        CheckoutOrder checkoutOrder = checkoutOrderRepository.findById(idCO).get();
//        BuktiPembayaran buktiPembayaran = new BuktiPembayaran();
//        buktiPembayaran.setIdCo(checkoutOrder.getId());
//        BuktiPembayaranDto buktiPembayaranDto = new BuktiPembayaranDto();
//        // jika tidak pakai model mapper maka perlu setter getter satu satu
//        buktiPembayaranDto.setIdCo(checkoutOrder.getId());
//        buktiPembayaranDto.setTanggalOrder(checkoutOrder.getTanggalOrder());
//        buktiPembayaranDto.setBatasTanggalPembayaran(checkoutOrder.getBatasTanggalPembayaran());
//        buktiPembayaranDto.setTipePembayaran(checkoutOrder.getTipePembayaran());
//        buktiPembayaranDto.setTotalHargalCi(checkoutOrder.getTotalHargalCi());
//
//        return buktiPembayaranDto;
//    }

    @GetMapping("/{idAja}")
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

    @PostMapping
    public BuktiPembayaranDto saveOrEditBuktiPembayaran(@RequestBody BuktiPembayaranDto buktiPembayaranDto) {
        BuktiPembayaran buktiPembayaran = modelMapper.map(buktiPembayaranDto, BuktiPembayaran.class);
        buktiPembayaran.setIdCo(buktiPembayaranDto.getIdCo());
        buktiPembayaran = buktiPembayaranService.saveBuktiPembayaranService(buktiPembayaran);
        BuktiPembayaranDto buktiPembayaranDtoNew = mapToDtoBP(buktiPembayaran);

        return buktiPembayaranDtoNew;
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

    @GetMapping("/{idBP}")
    public CheckoutOrderDto validateCheckoutOrder(@PathVariable Integer idBP) {
        BuktiPembayaran buktiPembayaran = buktiPembayaranRepository.findById(idBP).get();
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findById(buktiPembayaran.getIdCo()).get();
        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrder,CheckoutOrderDto.class);
        checkoutOrderDto.setIdCostumer(checkoutOrder.getIdCostumer());
        checkoutOrderDto.setId(checkoutOrder.getId());
        return checkoutOrderDto;
    }

    @PostMapping("/change-status-berhasil")
    public CheckoutOrder changeStatusBerhasil(@RequestBody CheckoutOrderDto checkoutOrderDto){
        CheckoutOrder checkoutOrder = modelMapper.map(checkoutOrderDto,CheckoutOrder.class);
        checkoutOrder.setStatusPesanan(PesananStatus.DIPROSES);
        checkoutOrder.setIdCostumer(checkoutOrderDto.getIdCostumer());
        checkoutOrder.setId(checkoutOrderDto.getId());

        List<CheckoutItem> checkoutItemList =checkoutItemRepository.findAllByIdOrder(checkoutOrder.getId());
        for (int i = 0; i<checkoutItemList.size(); i++){
            CheckoutItem checkoutItemId = checkoutItemList.get(i);
            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItemId.getIdKeranjang());
            Keranjang k = keranjang.get();
            k.setStatusKeranjang(ItemStatus.PAID.toString());
            keranjangRepository.save(k);

            // pengurangan stok buku otomatis jika sudah terbayar

            Book book = bookRepository.findById(k.getIdBuku()).get();
            book.setStokBuku(book.getStokBuku() - k.getKuantitasBuku());
            bookRepository.save(book);
        }


//        CheckoutOrder checkoutOrderChangeStatus = checkoutOrderRepository.save(checkoutOrder);

//        CheckoutOrder checkoutOrderNewStatus = new CheckoutOrder();
//        List<CheckoutItem> checkoutItemList = checkoutOrderChangeStatus.getItems();
//        for (int i = 0; i<checkoutItemList.size(); i++){
//            CheckoutItem checkoutItem = checkoutItemList.get(i);
//            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItem.getIdKeranjang());
//            Keranjang k = keranjang.get();
//            k.setStatusKeranjang(ItemStatus.PAID);
//            Keranjang keranjangNewStatus = keranjangRepository.save(k);
//            checkoutItem.setKeranjang(keranjangNewStatus);
//            checkoutItemRepository.save(checkoutItem);
//        }
        return checkoutOrderRepository.save(checkoutOrder);
    }

    @PostMapping("/change-status-gagal")
    public CheckoutOrder changeStatusGagal(@RequestBody CheckoutOrderDto checkoutOrderDto){
        CheckoutOrder checkoutOrder = modelMapper.map(checkoutOrderDto,CheckoutOrder.class);
        checkoutOrder.setStatusPesanan(PesananStatus.DIBATALKAN);
        checkoutOrder.setIdCostumer(checkoutOrderDto.getIdCostumer());
        checkoutOrder.setId(checkoutOrderDto.getId());

        List<CheckoutItem> checkoutItemList =checkoutItemRepository.findAllByIdOrder(checkoutOrder.getId());
        for (int i = 0; i<checkoutItemList.size(); i++){
            CheckoutItem checkoutItemId = checkoutItemList.get(i);
            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItemId.getIdKeranjang());
            Keranjang k = keranjang.get();
            k.setStatusKeranjang(ItemStatus.CANCELED.toString());
            keranjangRepository.save(k);
        }

//        CheckoutOrder checkoutOrderChangeStatus = checkoutOrderRepository.save(checkoutOrder);

//        CheckoutOrder checkoutOrderNewStatus = new CheckoutOrder();
//        List<CheckoutItem> checkoutItemList = checkoutOrderChangeStatus.getItems();
//        for (int i = 0; i<checkoutItemList.size(); i++){
//            CheckoutItem checkoutItem = checkoutItemList.get(i);
//            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItem.getIdKeranjang());
//            Keranjang k = keranjang.get();
//            k.setStatusKeranjang(ItemStatus.PAID);
//            Keranjang keranjangNewStatus = keranjangRepository.save(k);
//            checkoutItem.setKeranjang(keranjangNewStatus);
//            checkoutItemRepository.save(checkoutItem);
//        }
        return checkoutOrderRepository.save(checkoutOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        buktiPembayaranRepository.deleteById(id);
    }

//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<CheckoutOrder> getAllOrders() {
//        return checkoutOrderService.getAllOrders();
//    }


    //1. API = nampilin list item yg mau di checkout
    //2. proses centang item
    //3. tombol place order:
    //      1. nangkep apa aja yg mau dicentang
    //      2. buat json array yg berisi item yg di centang
}