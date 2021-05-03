package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.BookDto;
import com.example.onlinebookstore.model.dto.BuktiPembayaranDto;
import com.example.onlinebookstore.model.dto.CheckoutOrderDto;
import com.example.onlinebookstore.model.entity.*;
import com.example.onlinebookstore.repository.BuktiPembayaranRepository;
import com.example.onlinebookstore.repository.CheckoutItemRepository;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.BuktiPembayaranService;
import com.example.onlinebookstore.service.CheckoutOrderService;
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
    public CheckoutOrderRepository checkoutOrderRepository;
    @Autowired
    public CheckoutItemRepository checkoutItemRepository;
    @Autowired
    public BuktiPembayaranRepository buktiPembayaranRepository;
    @Autowired
    public BuktiPembayaranService buktiPembayaranService;
    @Autowired
    public ModelMapper modelMapper;


    @GetMapping("/{idPembayaran}")
    public BuktiPembayaranDto getById(@PathVariable Integer id) {
        BuktiPembayaran buktiPembayaran = buktiPembayaranRepository.findById(id).get();
        BuktiPembayaranDto buktiPembayaranDto = modelMapper.map(buktiPembayaran,BuktiPembayaranDto.class);
        return buktiPembayaranDto;
    }

    @PostMapping
    public BuktiPembayaranDto saveOrEditBuktiPembayaran(@RequestBody BuktiPembayaranDto buktiPembayaranDto) {
        BuktiPembayaran buktiPembayaran = modelMapper.map(buktiPembayaranDto, BuktiPembayaran.class);
        buktiPembayaran.setIdCo(buktiPembayaranDto.getIdCo());
        buktiPembayaran = buktiPembayaranService.saveBuktiPembayaranService(buktiPembayaran);
        BuktiPembayaranDto buktiPembayaranDtoNew = mapToDto(buktiPembayaran);

        return buktiPembayaranDtoNew;
    }

    private BuktiPembayaranDto mapToDto(BuktiPembayaran buktiPembayaran) {
        BuktiPembayaranDto buktiPembayaranDto = modelMapper.map(buktiPembayaran, BuktiPembayaranDto.class);

        buktiPembayaranDto.setIdCo(buktiPembayaran.getCheckoutOrder().getId());
        buktiPembayaranDto.setTanggalCo(buktiPembayaran.getCheckoutOrder().getTanggalCo());
        buktiPembayaranDto.setTotalHargalCi(buktiPembayaran.getCheckoutOrder().getTotalHargalCi());
        buktiPembayaranDto.setBatasTanggalPembayaran(buktiPembayaran.getCheckoutOrder().getBatasTanggalPembayaran());
        buktiPembayaranDto.setTipePembayaran(buktiPembayaran.getCheckoutOrder().getTipePembayaran());

        buktiPembayaranDto.setId(buktiPembayaran.getId());

        return buktiPembayaranDto;
    }

    @GetMapping("/{idAja}")
    public BuktiPembayaranDto getCheckoutOrder(@PathVariable Integer idCO) {
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findById(idCO).get();
        BuktiPembayaran buktiPembayaran = new BuktiPembayaran();
        buktiPembayaran.setIdCo(checkoutOrder.getId());
        BuktiPembayaranDto buktiPembayaranDto = new BuktiPembayaranDto();
        // jika tidak pakai model mapper maka perlu setter getter satu satu
        buktiPembayaranDto.setIdCo(checkoutOrder.getId());
        buktiPembayaranDto.setTanggalCo(checkoutOrder.getTanggalCo());
        buktiPembayaranDto.setBatasTanggalPembayaran(checkoutOrder.getBatasTanggalPembayaran());
        buktiPembayaranDto.setTipePembayaran(checkoutOrder.getTipePembayaran());
        buktiPembayaranDto.setTotalHargalCi(checkoutOrder.getTotalHargalCi());

        return buktiPembayaranDto;
    }

    @GetMapping("/{idCO}")
    public CheckoutOrderDto validateCheckoutOrder(@PathVariable Integer idCO) {
        BuktiPembayaran buktiPembayaran = buktiPembayaranRepository.findByIdCo(idCO);
        Optional<CheckoutOrder> checkoutOrder = checkoutOrderRepository.findById(buktiPembayaran.getIdCo());
        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrder,CheckoutOrderDto.class);
        return checkoutOrderDto;
    }

    @PostMapping("/change-status")
    public CheckoutOrder changeStatus(@RequestBody CheckoutOrderDto checkoutOrderDto){
        CheckoutOrder checkoutOrder = modelMapper.map(checkoutOrderDto,CheckoutOrder.class);
        checkoutOrder.setStatusPesanan(PesananStatus.DIPROSES);
        CheckoutOrder checkoutOrderChangeStatus = checkoutOrderRepository.save(checkoutOrder);

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
        return checkoutOrderChangeStatus;
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