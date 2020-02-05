# Springboot + Micromiter Prometheus Registry
## Config 
- 1 Số config trong `application.properties`
- `BatchJobMetric` : cập nhật giá trị metric    
    - Lưu ý việc cập nhật metric được chạy ở 1 process khác với endpoint metric. 
    (tức endpoint metric sẽ trả về kết quả đã được tính toán ở 1 process khác trước đó )
- Khai báo MetricFilter
    - Cách 1: tạo bean `MeterFilter`
    ```java
        @Bean
        public MeterFilter meterFilter() 
    ```
    - Cách 2: khai báo trong file `application.properties`
    ```properties
    management.metrics.enable.jvm=false
    ```
- Đổi port endpoint metric, độc lập với port webservice logic
    ```properties
    server.port=8080
    management.server.port=8090
    ```
- All metrics are exposed at `/actuator/prometheus`