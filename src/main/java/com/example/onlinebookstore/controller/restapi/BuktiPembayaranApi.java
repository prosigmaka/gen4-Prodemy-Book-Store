package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.BookDto;
import com.example.onlinebookstore.model.dto.BuktiPembayaranDto;
import com.example.onlinebookstore.model.dto.CheckoutOrderDto;
import com.example.onlinebookstore.model.entity.Book;
import com.example.onlinebookstore.model.entity.BuktiPembayaran;
import com.example.onlinebookstore.model.entity.CheckoutOrder;
import com.example.onlinebookstore.repository.BuktiPembayaranRepository;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.BuktiPembayaranService;
import com.example.onlinebookstore.service.CheckoutOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/co")
public class BuktiPembayaranApi {
    @Autowired
    public CheckoutOrderRepository checkoutOrderRepository;
    @Autowired
    public BuktiPembayaranRepository buktiPembayaranRepository;
    @Autowired
    public BuktiPembayaranService buktiPembayaranService;
    @Autowired
    public ModelMapper modelMapper;


    @GetMapping("/{id}")
    public BuktiPembayaran getById(@PathVariable Integer id) {
        return buktiPembayaranRepository.findById(id).get();
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
        buktiPembayaranDto.setTanggalCi(buktiPembayaran.getCheckoutOrder().getTanggalCi());
        buktiPembayaranDto.setTotalHargalCi(buktiPembayaran.getCheckoutOrder().getTotalHargalCi());
        buktiPembayaranDto.setBatasTanggalPembayaran(buktiPembayaran.getCheckoutOrder().getBatasTanggalPembayaran());
        buktiPembayaranDto.setTipePembayaran(buktiPembayaran.getCheckoutOrder().getTipePembayaran());

        buktiPembayaranDto.setId(buktiPembayaran.getId());

        return buktiPembayaranDto;
    }

    @GetMapping("/{idAja}")
    public BuktiPembayaranDto getCheckoutOrder(@PathVariable Integer idAja) {
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findById(idAja).get();
        BuktiPembayaran buktiPembayaran = new BuktiPembayaran();
        buktiPembayaran.setIdCo(checkoutOrder.getId());
        BuktiPembayaranDto buktiPembayaranDto = new BuktiPembayaranDto();
        // jika tidak pakai model mapper maka perlu setter getter satu satu
        buktiPembayaranDto.setIdCo(checkoutOrder.getId());
        buktiPembayaranDto.setTanggalCi(checkoutOrder.getTanggalCi());
        buktiPembayaranDto.setBatasTanggalPembayaran(checkoutOrder.getBatasTanggalPembayaran());
        buktiPembayaranDto.setTipePembayaran(checkoutOrder.getTipePembayaran());
        buktiPembayaranDto.setTotalHargalCi(checkoutOrder.getTotalHargalCi());

        return buktiPembayaranDto;
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