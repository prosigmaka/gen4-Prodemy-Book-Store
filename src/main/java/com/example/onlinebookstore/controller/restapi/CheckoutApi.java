package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.CheckoutItemDto;
import com.example.onlinebookstore.model.dto.CheckoutOrderDto;
import com.example.onlinebookstore.model.dto.RequestListOrderDTO;
import com.example.onlinebookstore.model.entity.CheckoutItem;
import com.example.onlinebookstore.model.entity.CheckoutOrder;
import com.example.onlinebookstore.model.entity.ItemStatus;
import com.example.onlinebookstore.model.entity.Keranjang;
import com.example.onlinebookstore.repository.CheckoutItemRepository;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.CheckoutService;
import com.example.onlinebookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/c")
public class CheckoutApi {

    @Autowired
    public UserService userService;
    @Autowired
    public CheckoutService checkoutService;
    @Autowired
    public KeranjangRepository keranjangRepository;
    @Autowired
    public CheckoutItemRepository checkoutItemRepository;
    @Autowired
    public CheckoutOrderRepository checkoutOrderRepository;
    @Autowired
    public ModelMapper modelMapper;


    @PostMapping(path = "/checkout")
    public @ResponseBody
    CheckoutOrder checkoutOrder(@RequestBody RequestListOrderDTO requestListOrderDTO) {
        return checkoutService.checkoutOrder(requestListOrderDTO);
    }

    @PostMapping(path = "/checkout-cancel")
    public void checkoutCancel() {
        CheckoutOrder getCheckoutOrder = checkoutOrderRepository.findAllByIdCostumerAndTanggalCoOrderById(userService.idCustomerLogIn());
        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllByIdOrderAndTanggalCiOrderById(getCheckoutOrder.getId());

        for (int i = 0; i<checkoutItemList.size(); i++){
            CheckoutItem checkoutItemId = checkoutItemList.get(i);
            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItemId.getIdKeranjang());
            Keranjang k = keranjang.get();
            k.setStatusKeranjang(ItemStatus.ADD_TO_CART);
            keranjangRepository.save(k);
            checkoutItemRepository.delete(checkoutItemId);
        }
        checkoutOrderRepository.delete(getCheckoutOrder);
    }

    @GetMapping("/co-checkoutpage")
    public CheckoutOrderDto checkoutOrderDataForCOPage(){
        CheckoutOrder getCheckoutOrder = checkoutOrderRepository.findAllByIdCostumerAndTanggalCoOrderById(userService.idCustomerLogIn());

        CheckoutOrderDto checkoutOrderDto = modelMapper.map(getCheckoutOrder, CheckoutOrderDto.class);
        checkoutOrderDto.setIdCostumer(getCheckoutOrder.getIdCostumer());
        checkoutOrderDto.setId(getCheckoutOrder.getId());

        return checkoutOrderDto;

    }

    @GetMapping("/ci-checkoutpage")
    public List<CheckoutItemDto> checkoutItemListForCOPage(){
        CheckoutOrder getCheckoutOrder = checkoutOrderRepository.findAllByIdCostumerAndTanggalCoOrderById(userService.idCustomerLogIn());
        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllByIdOrderAndTanggalCiOrderById(getCheckoutOrder.getId());

        List<CheckoutItemDto> checkoutItemDtoList =
                checkoutItemList.stream()
                        .map(cI -> mapToDtoCI(cI))
                        .collect(Collectors.toList());
        return checkoutItemDtoList;

    }

    private CheckoutItemDto mapToDtoCI(CheckoutItem checkoutItem) {
        CheckoutItemDto checkoutItemDto = modelMapper.map(checkoutItem, CheckoutItemDto.class);

        checkoutItemDto.setIdKeranjang(checkoutItem.getIdKeranjang());
        checkoutItemDto.setJudulBuku(checkoutItem.getKeranjang().getBook().getJudulBuku());
        checkoutItemDto.setKuantitasBuku(checkoutItem.getKeranjang().getKuantitasBuku());
        checkoutItemDto.setKategoriBuku(checkoutItem.getKeranjang().getBook().getCategory().getNamaKategori());
        checkoutItemDto.setHargaBuku(checkoutItem.getKeranjang().getBook().getHargaBuku());
        checkoutItemDto.setSubTotalHargaBuku(checkoutItem.getKeranjang().getSubTotalHargaBuku());

//        checkoutItemDto.setTanggalCo(checkoutItem.getCheckoutOrder().getTanggalCo());
//        checkoutItemDto.setTanggalOrder(checkoutItem.getCheckoutOrder().getTanggalOrder());
//        checkoutItemDto.setTipePembayaran(checkoutItem.getCheckoutOrder().getTipePembayaran());
//        checkoutItemDto.setBankPilihan(checkoutItem.getCheckoutOrder().getBankPilihan());
//        checkoutItemDto.setTotalHargalCi(checkoutItem.getCheckoutOrder().getTotalHargalCi());
//        checkoutItemDto.setBatasTanggalPembayaran(checkoutItem.getCheckoutOrder().getBatasTanggalPembayaran());
//        checkoutItemDto.setStatusPesanan(checkoutItem.getCheckoutOrder().getStatusPesanan());


        checkoutItemDto.setId(checkoutItem.getId());
        checkoutItemDto.setIdOrder(checkoutItem.getIdOrder());

        return checkoutItemDto;
    }
}