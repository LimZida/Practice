package practice.toyproject.util.AWS;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * title : S3Config
 * description : AWS S3 정보를 가져와 연결
 *
 * reference : S3 연동하기 : https://velog.io/@_koiil/Springboot-AWS-S3%EB%A1%9C-%ED%8C%8C%EC%9D%BC-%EC%A0%80%EC%9E%A5%EC%86%8C-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0
 *             com.amazonaws.SdkClientException: Failed to connect to service endpoint EC2 오류 : https://lemontia.tistory.com/1006 , https://thalals.tistory.com/289
 *
 * author : 임현영
 * date : 2023.07.04
 **/
@Configuration
public class S3Config {
    private final String accessKey;
    private final String secretKey;
    private final String region;

    @Autowired
    public S3Config( @Value("${cloud.aws.credentials.access-key}")String accessKey, @Value("${cloud.aws.credentials.secret-key}")String secretKey, @Value("${cloud.aws.region.static}") String region){
        this.accessKey=accessKey;
        this.secretKey=secretKey;
        this.region=region;
        
    }

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
        AmazonS3Client build = (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

//        AmazonS3Client build = (AmazonS3Client) AmazonS3ClientBuilder.standard().build();
        return build;
    }
}
