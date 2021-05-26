# Using a Polling Consumer with Spring Cloud Stream & Solace

## Introduction
Spring Cloud Stream (SCSt) is a framework in the Spring family. It provides a flexible programming model for building broker-agnostic Event Driven Architectures.

Although SCSt consumers generally process messages immediately upon receipt, it may occasionally be necessary to periodically poll an `input` for messages using `polled consumers`. This project demonstrates the same.

## Assumptions
The reader is familiar with basic Spring Cloud Stream concepts. If not, read [this](https://docs.spring.io/spring-cloud-stream/docs/3.1.2/reference/html/spring-cloud-stream.html#_main_concepts) first.

## Design
The application uses a `polled consumer` to retrieve a message every `5s` from an `input` and print it to the console.

## Notes
*   The **published** Spring Cloud Streams v3.0 documentation uses a deprecated approach for polling an `input`. The updated approach can be found in the documentation [Github](https://github.com/spring-cloud/spring-cloud-stream/blob/main/docs/src/main/asciidoc/spring-cloud-stream.adoc#spring-cloud-streams-overview-using-polled-consumers).
*   The "default poller" mentioned in the documentation is **not** applicable to this usecase. It is used to invoke `suppliers` on a periodic basis.

## References
* [Polled Consumers](https://github.com/spring-cloud/spring-cloud-stream/blob/main/docs/src/main/asciidoc/spring-cloud-stream.adoc#spring-cloud-streams-overview-using-polled-consumers)

 