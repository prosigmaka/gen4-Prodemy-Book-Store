package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.CheckoutItemDto;
import com.example.onlinebookstore.model.dto.CheckoutOrderDto;
import com.example.onlinebookstore.model.dto.RequestListOrderDTO;
import com.example.onlinebookstore.model.entity.CheckoutItem;
import com.example.onlinebookstore.model.entity.ItemStatus;
import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.repository.CheckoutItemRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.CheckoutOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ci")
public class CheckoutItemApi {

    @Autowired
    public CheckoutOrderService checkoutOrderService;
    @Autowired
    public KeranjangRepository keranjangRepository;
    @Autowired
    public CheckoutItemRepository checkoutItemRepository;
    @Autowired
    public ModelMapper modelMapper;


    @DeleteMapping
    public void cancelCheckoutItem(){
        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllIdOrderByTanggalCi();

        for (int i = 0; i<checkoutItemList.size(); i++){
//        for (CheckoutItem checkoutItemLoop : checkoutItemList()) {
//        for (IdKeranjangDTO idKeranjangDTO : requestListOrderDTO.getIdKeranjangDTOList()) {
//            System.out.println(idKeranjangDTO.getId());

            CheckoutItem checkoutItemId = checkoutItemList.get(i);
            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItemId.getIdKeranjang());
            Keranjang k = keranjang.get();
            k.setStatusKeranjang(ItemStatus.ADD_TO_CART);
            Keranjang keranjangNewStatus = keranjangRepository.save(k);
            checkoutItemId.setKeranjang(keranjangNewStatus);
            checkoutItemRepository.delete(checkoutItemId);
        }
    }

//    @GetMapping
//    public List<CheckoutItemDto> getCheckoutItemList(){
//        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllIdOrderByTanggalCi();
//
//        for (int i = 0; i<checkoutItemList.size(); i++){
////        for (CheckoutItem checkoutItemLoop : checkoutItemList()) {
////        for (IdKeranjangDTO idKeranjangDTO : requestListOrderDTO.getIdKeranjangDTOList()) {
////            System.out.println(idKeranjangDTO.getId());
//
//            CheckoutItem checkoutItemId = checkoutItemList.get(i);
//            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItemId.getIdKeranjang());
//            Keranjang k = keranjang.get();
//            k.setStatusKeranjang(ItemStatus.ADD_TO_CART);
//            Keranjang keranjangNewStatus = keranjangRepository.save(k);
//            checkoutItemId.setKeranjang(keranjangNewStatus);
//            checkoutItemRepository.delete(checkoutItemId);
//        }
//
//
//        CheckoutItem checkoutItem = new CheckoutItem();
//        List<CheckoutItemDto> checkoutItemDtoList =
//                checkoutItemList.stream()
//                        .map(cI -> mapToDto(checkoutItem))
//                        .collect(Collectors.toList());
//        return checkoutItemDtoList;
//    }

    private CheckoutItemDto mapToDto(CheckoutItem checkoutItem) {
        CheckoutItemDto checkoutItemDto = modelMapper.map(checkoutItem, CheckoutItemDto.class);

        checkoutItemDto.setId(checkoutItemDto.getId());

        return checkoutItemDto;
    }

    @PostMapping(path = "/checkout-item")
    public @ResponseBody
    List<CheckoutItemDto> checkoutItem(@RequestBody RequestListOrderDTO requestListOrderDTO) {
        return checkoutOrderService.checkoutItem(requestListOrderDTO);

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