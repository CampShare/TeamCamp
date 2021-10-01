package TeamCamp.demo.service;

import TeamCamp.demo.domain.model.product.Product;
import TeamCamp.demo.domain.model.product.ProductState;
import TeamCamp.demo.domain.model.trade.Trade;
import TeamCamp.demo.domain.model.trade.TradeStatus;
import TeamCamp.demo.domain.model.trade.TransactionMethod;
import TeamCamp.demo.domain.model.users.User;
import TeamCamp.demo.domain.model.users.UserLevel;
import TeamCamp.demo.domain.model.users.UserStatus;
import TeamCamp.demo.domain.model.users.user.address.Address;
import TeamCamp.demo.domain.repository.ProductRepository;
import TeamCamp.demo.domain.repository.UserRepository;
import TeamCamp.demo.dto.ProductDto;
import TeamCamp.demo.dto.TradeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TradeServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private TradeService tradeService;


    private User user0(){
        return User.builder()
                .id(1L)
                .email("rdj1014@naver.com")
                .password("ehdwo991014")
                .phone("01033234455")
                .nickname("ryu")
                .nicknameModifiedDate(LocalDateTime.now())
                .userLevel(UserLevel.AUTH)
                .userStatus(UserStatus.NORMAL)
                .build();
    }

    private User user1(){
        return User.builder()
                .id(2L)
                .email("rdj10149@naver.com")
                .password("test1234")
                .phone("01037734455")
                .nickname("ryudd")
                .nicknameModifiedDate(LocalDateTime.now())
                .userLevel(UserLevel.AUTH)
                .userStatus(UserStatus.NORMAL)
                .build();
    }


    private String ProductOriginImagePath = "https://cusproduct.s3.ap-northeast-2.amazonaws.com/9b9a6239-fbae-4ce3-bba3-731ddfc1fea8.jpg";
    private String ProductThumbnailImagePath = "https://cusproduct.s3.ap-northeast-2.amazonaws.com/9b9a6239-fbae-4ce3-bba3-731ddfc1fea8.jpg";
    private String ProductChangedOriginImagePath =  "https://cusproduct.s3.ap-northeast-2.amazonaws.com/9b9a6239-fbae-4ce3-bba3-731ddfc1fea8.jpg";
    private String ProductChangedThumbnailImagePath =  "https://cusproduct.s3.ap-northeast-2.amazonaws.com/9b9a6239-fbae-4ce3-bba3-731ddfc1fea8.jpg";

    private ProductDto.SaveRequest createProductDto(){
        return ProductDto.SaveRequest.builder()
                .name("텐트")
                .user(user0())
                .productDescription("good")
                .productState(ProductState.BEST)
                .originImagePath(ProductOriginImagePath)
                .thumbnailImagePath(ProductThumbnailImagePath)
                .build();
    }
    private Product createProduct(){
        return Product.builder()
                .id(1L)
                .name("텐트")
                .user(user0())
                .productDescription("good")
                .productState(ProductState.BEST)
                .originImagePath(ProductOriginImagePath)
                .thumbnailImagePath(ProductThumbnailImagePath)
                .build();
    }

    private List<Trade>createTrades(){
        User user0 = user0();
        User user1 = user1();
        Address address = new Address(1L,"내집","대정로1","101동1004호","56432");
        Product product = createProduct();
        List<Trade> list = new ArrayList<>();

        Trade sale1 = Trade.builder()
                .seller(user0)
                .buyer(null)
                .product(product)
                .tradeStatus(TradeStatus.BID)
                .returnAddress(address)
                .transactionMethod(TransactionMethod.ALL)
                .shippingAddress(null)
                .build();
        list.add(sale1);

        Trade purchase1 = Trade.builder()
                .seller(null)
                .buyer(user0)
                .product(product)
                .tradeStatus(TradeStatus.BID)
                .returnAddress(address)
                .transactionMethod(TransactionMethod.ALL)
                .shippingAddress(null)
                .build();
        list.add(sale1);

        Trade sale2 = Trade.builder()
                .seller(user1)
                .buyer(null)
                .product(product)
                .tradeStatus(TradeStatus.BID)
                .returnAddress(address)
                .transactionMethod(TransactionMethod.ALL)
                .shippingAddress(null)
                .build();
        list.add(sale1);

        Trade purchase2 = Trade.builder()
                .buyer(user1)
                .seller(null)
                .product(product)
                .tradeStatus(TradeStatus.BID)
                .returnAddress(address)
                .transactionMethod(TransactionMethod.ALL)
                .shippingAddress(null)
                .build();
        list.add(sale1);

        return list;
    }

    @Test
    void getResourceForTrade()throws Exception{
        //given
        String email = "rdj1014@naver.com";
        Long productId = 1L;
        User user = user0();
        Product product = createProduct();

        //when
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        TradeDto.TradeResource tradeResource =  tradeService.makeTradeResource(user,product);
        //then

        //assertThat(tradeResource.getProductInfoByTrade().getBuyPrice()).isEqualTo();

    }
}