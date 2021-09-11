package TeamCamp.demo.domain.model.product;

import lombok.*;
import TeamCamp.demo.domain.model.users.user.BaseTimeEntity;
import TeamCamp.demo.domain.model.users.user.User;
import TeamCamp.demo.dto.ProductDto.ProductInfoResponse;
import TeamCamp.demo.dto.ProductDto.SaveRequest;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Product extends BaseTimeEntity {

    @Id@GeneratedValue
    private Long id;

    private String name;

    private String salePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String productDescription;

    private String releasePrice;


    @Enumerated(EnumType.STRING)
    private ProductState productState;

    private String originImagePath;
    private String thumbnailImagePath;


    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;


    public ProductInfoResponse toProductInfoResponse(){
        return ProductInfoResponse.builder()
                .id(this.id)
                .name(this.name)
                .user(this.user)
                .salePrice(this.salePrice)
                .productDescription(this.productDescription)
                .releasePrice(this.releasePrice)
                .productState(this.productState)
                .originImagePath(this.originImagePath)
                .thumbnailImagePath(this.thumbnailImagePath)
                .transactionMethod(this.transactionMethod)
                .build();
    }

    public void update(SaveRequest request){
        this.name =request.getName();
        this.salePrice = request.getSalePrice();
        this.productDescription = request.getProductDescription();
        this.releasePrice = request.getReleasePrice();
        this.productState = request.getProductState();
        this.originImagePath = request.getOriginImagePath();
        this.thumbnailImagePath = request.getThumbnailImagePath();
        this.transactionMethod = request.getTransactionMethod();
    }

}
