package TeamCamp.demo.domain.model.product;

import TeamCamp.demo.domain.model.trade.Trade;
import TeamCamp.demo.dto.ProductDto;
import lombok.*;
import TeamCamp.demo.domain.model.users.BaseTimeEntity;
import TeamCamp.demo.domain.model.users.User;
import TeamCamp.demo.dto.ProductDto.ProductInfoResponse;
import TeamCamp.demo.dto.ProductDto.SaveRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Product extends BaseTimeEntity {

    @Id@GeneratedValue
    private Long id;

    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Trade> trades = new ArrayList<>();

    private String productDescription;


    @Enumerated(EnumType.STRING)
    private ProductState productState;

    private String originImagePath;
    private String thumbnailImagePath;


    public ProductInfoResponse toProductInfoResponse(){
        return ProductInfoResponse.builder()
                .id(this.id)
                .name(this.name)
                .user(this.user)
                .productDescription(this.productDescription)
                .productState(this.productState)
                .originImagePath(this.originImagePath)
                .build();
    }


   public ProductDto.ProductInfoByTrade toProductInfoByTrade(User currentUser){
        return ProductDto.ProductInfoByTrade.builder()
                .id(this.id)
                .name(this.name)
                .user(this.user)
                .productDescription(this.productDescription)
                .productState(this.productState)
                .build();
   }

    public void update(SaveRequest request){
        this.name =request.getName();
        this.productDescription = request.getProductDescription();
        this.productState = request.getProductState();
        this.originImagePath = request.getOriginImagePath();
        this.thumbnailImagePath = request.getThumbnailImagePath();
    }

}
