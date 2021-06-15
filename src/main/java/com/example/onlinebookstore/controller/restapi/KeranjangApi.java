package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.CartUpdateDto;
import com.example.onlinebookstore.model.dto.DirectAddToCartDto;
import com.example.onlinebookstore.model.entity.ItemStatus;
import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.KeranjangService;
import com.example.onlinebookstore.service.UserService;
import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.dto.KeranjangDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class KeranjangApi {

    @Autowired
    private KeranjangService keranjangService;
    @Autowired
    private KeranjangRepository keranjangRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<KeranjangDto> getAll() {
        Long num = 1L;
        List<Keranjang> keranjangList = keranjangRepository.findAllByIdCustomerAndStatusKeranjang(num, ItemStatus.ADD_TO_CART.toString());
//        List<Keranjang> keranjangList = keranjangRepository.findAllByIdCustomerAndStatusKeranjang(userService.idCustomerLogIn(), ItemStatus.ADD_TO_CART.toString());
        List<KeranjangDto> keranjangDtoList = keranjangList
                .stream()
                .map(cart -> mappingToKeranjangDto(cart))
                .collect(Collectors.toList());
        return keranjangDtoList;
    }

//    @PostMapping()
//    public List<DirectAddToCartDto> simpanKeranjang(@RequestBody CartUpdateDto cartUpdateDto){
//        List<DirectAddToCartDto> directAddToCartDtoList = cartUpdateDto.getAddToCart()
//                .stream()
//                .map(cart -> mapToCartDto(cart)).collect(Collectors.toList());
//        return directAddToCartDtoList;
//    }

    @PostMapping()
    public List<DirectAddToCartDto> simpanKeranjang(@RequestBody CartUpdateDto cartUpdateDto) {
        List<DirectAddToCartDto> listCart = new ArrayList<>();
        for (DirectAddToCartDto cart : cartUpdateDto.getAddToCart()) {
            listCart.add(mapToCartDto(cart));
        }
        return null;
    }

//    private DirectAddToCartDto mapToCartDto(DirectAddToCartDto dto) {
//        Long num = 1L;
//        DirectAddToCartDto directAddToCartDto = modelMapper.map(dto, DirectAddToCartDto.class);
//        Keranjang keranjang = modelMapper.map(directAddToCartDto, Keranjang.class);
////        keranjang.setIdCustomer(userService.idCustomerLogIn());
//        keranjang.setIdCustomer(num);
//        keranjangService.saveToCartDirect(keranjang, directAddToCartDto);
//        return keranjang;
//    }

    private DirectAddToCartDto mapToCartDto(DirectAddToCartDto dto) {
        Long num = 1L;
        DirectAddToCartDto directAddToCartDto = modelMapper.map(dto, DirectAddToCartDto.class);
        Keranjang keranjang = modelMapper.map(directAddToCartDto, Keranjang.class);
//        keranjang.setIdCustomer(userService.idCustomerLogIn());
        keranjang.setIdCustomer(num);
        keranjangService.saveToCartDirect(keranjang, directAddToCartDto);
        return modelMapper.map(keranjang, DirectAddToCartDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        keranjangRepository.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAll() {
        keranjangRepository.deleteAll();
    }


    private KeranjangDto mappingToKeranjangDto(Keranjang keranjang) {
        KeranjangDto keranjangDto = modelMapper.map(keranjang, KeranjangDto.class);
        keranjangDto.setJudulBuku(keranjang.getBook().getJudulBuku());
        keranjangDto.setHargaBuku(keranjang.getBook().getHargaBuku());
        keranjangDto.setGambar(keranjang.getBook().getGambar());
        keranjangDto.setSubTotalHargaBuku(keranjang.getSubTotalHargaBuku());
        return keranjangDto;
    }


}