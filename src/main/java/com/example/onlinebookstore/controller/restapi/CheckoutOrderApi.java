package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.CheckoutOrderDto;
import com.example.onlinebookstore.model.dto.RequestListOrderDTO;
import com.example.onlinebookstore.model.entity.BuktiPembayaran;
import com.example.onlinebookstore.model.entity.CheckoutItem;
import com.example.onlinebookstore.model.entity.CheckoutOrder;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.CheckoutOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/co")
public class CheckoutOrderApi {
    @Autowired
    public CheckoutOrderService checkoutOrderService;
    @Autowired
    public CheckoutOrderRepository checkoutOrderRepository;
    @Autowired
    public KeranjangRepository keranjangRepository;
    @Autowired
    public ModelMapper modelMapper;

    @GetMapping
    public List<CheckoutOrderDto> getCheckoutOrderList() {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllOrderById();
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDto(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    private CheckoutOrderDto mapToDto(CheckoutOrder checkoutOrder) {
        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrder, CheckoutOrderDto.class);

//        checkoutOrderDto.setTanggalCi(checkoutOrder.getTanggalCi());
//        checkoutOrderDto.setTotalHargalCi(checkoutOrder.getTotalHargalCi());
//        checkoutOrderDto.setTipePembayaran(checkoutOrder.getTipePembayaran());
//        checkoutOrderDto.setBatasTanggalPembayaran(checkoutOrder.getBatasTanggalPembayaran());
//        checkoutOrderDto.setStatusPesanan(checkoutOrder.getStatusPesanan());
//
//        checkoutOrderDto.setItems(checkoutOrder.getItems().get());

        checkoutOrderDto.setId(checkoutOrder.getId());

        return checkoutOrderDto;
    }

//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<CheckoutOrder> getAllOrders() {
//        return checkoutOrderService.getAllOrders();
//    }

    @PostMapping(path = "/insert")
    public @ResponseBody
    CheckoutOrder insertOrder(@RequestBody CheckoutItem checkoutItem) {
//    CheckoutOrder insertOrder(@RequestBody RequestListOrderDTO requestListOrderDTO) {
        return checkoutOrderService.insertOrder(checkoutItem);

    }

//    @PostMapping(path = "/checkout")
//    public @ResponseBody
//    RequestOrderDTO checkoutItem(@PathVariable Integer idCart) {
//        Keranjang keranjang = keranjangRepository.findById(idCart).get();
//        RequestOrderDTO requestOrderDTO = new RequestOrderDTO();
//        requestOrderDTO.getCartsId().add(keranjang.getId());
//        return requestOrderDTO;
////        return checkoutOrderService.checkoutItem(requestOrderDTO);
//
//    }


    //1. API = nampilin list item yg mau di checkout
    //2. proses centang item
    //3. tombol place order:
    //      1. nangkep apa aja yg mau dicentang
    //      2. buat json array yg berisi item yg di centang
}