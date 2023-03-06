FROM openjdk:11
EXPOSE 8086:8086
RUN mkdir /app
COPY ./build/install/order-service/ /app/
WORKDIR /app/bin
CMD ["./order-service"]
