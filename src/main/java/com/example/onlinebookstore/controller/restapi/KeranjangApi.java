package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.CartUpdateDto;
import com.example.onlinebookstore.model.dto.DirectAddToCartDto;
import com.example.onlinebookstore.model.dto.LoginDto;
import com.example.onlinebookstore.model.entity.ItemStatus;
import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.service.KeranjangService;
import org.modelmapper.ModelMapper;
import com.example.onlinebookstore.model.dto.KeranjangDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.onlinebookstore.model.entity.PesananStatus;

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
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Long currentIdCustomer(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Long idCustomer = userRepository.findIdByUserName(userName);
        return idCustomer;
    }

    @GetMapping
    public List<KeranjangDto> getAll() {
        List<Keranjang> keranjangList = keranjangRepository.findAllByIdCustomerAndStatus(currentIdCustomer(), String.valueOf(ItemStatus.ADD_TO_CART));
        List<KeranjangDto> keranjangDtoList = keranjangList
                .stream()
                .map(cart -> mappingToKeranjangDto(cart))
                .collect(Collectors.toList());
        return keranjangDtoList;
    }
    @GetMapping("/idUser")
    public Long getIdUser(LoginDto loginDto){
        return loginDto.getIdUserLogin();
    }

    @PostMapping()
    public List<DirectAddToCartDto> simpanKeranjang(@RequestBody CartUpdateDto cartUpdateDto){
        List<DirectAddToCartDto> directAddToCartDtoList = cartUpdateDto.getAddToCart()
                .stream()
                .map(cart -> mapToCartDto(cart)).collect(Collectors.toList());
        return directAddToCartDtoList;
    }

    private DirectAddToCartDto mapToCartDto(DirectAddToCartDto dto){
        DirectAddToCartDto directAddToCartDto = modelMapper.map(dto, DirectAddToCartDto.class);
        Keranjang keranjang = modelMapper.map(directAddToCartDto, Keranjang.class);
        keranjang.setIdCustomer(currentIdCustomer());
        keranjangService.saveToCartDirect(keranjang, directAddToCartDto);
        return keranjang;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        keranjangRepository.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAll(){
        keranjangRepository.deleteAll();
    }


    private KeranjangDto mappingToKeranjangDto(Keranjang keranjang){
        KeranjangDto keranjangDto = modelMapper.map(keranjang, KeranjangDto.class);
        keranjangDto.setJudulBuku(keranjang.getBook().getJudulBuku());
        keranjangDto.setHargaBuku(keranjang.getBook().getHargaBuku());
        keranjangDto.setGambar(keranjang.getBook().getGambar());
        keranjangDto.setSubTotalHargaBuku(keranjang.getSubTotalHargaBuku());
        return keranjangDto;
    }


}