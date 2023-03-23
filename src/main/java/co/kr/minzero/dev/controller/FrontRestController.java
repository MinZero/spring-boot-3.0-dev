package co.kr.minzero.dev.controller;

import co.kr.minzero.dev.model.MorphInfo;
import co.kr.minzero.dev.repository.MorphInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePartEvent;
import org.springframework.http.codec.multipart.FormPartEvent;
import org.springframework.http.codec.multipart.PartEvent;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
public class FrontRestController {

    @Autowired
    private MorphInfoRepository morphInfoRepository;

    @GetMapping("/list")
    public Flux<MorphInfo> morphList(Model model) {
        return morphInfoRepository.findAll().delayElements(Duration.ofMillis(50));
    }

    @PostMapping("file-upload-part-events")
    public ResponseEntity<Flux<String>> handlePartsEvents(@RequestBody Flux<PartEvent> allPartsEvent) {
        var result = allPartsEvent.windowUntil(PartEvent::isLast)
                .concatMap(p -> {
                    return p.switchOnFirst((signal, partEvents) -> {
                        if (signal.hasValue()) {
                            PartEvent event = signal.get();
                            if (event instanceof FormPartEvent formEvent) {

                                String name = formEvent.name();
                                String value = formEvent.value();
                                log.info("form name/value: {} / {}", name, value);

                                // handle form field
                                return Mono.just(value + "\n");
                            } else if (event instanceof FilePartEvent fileEvent) {

                                String filename = fileEvent.filename();
                                String formName = fileEvent.name();
                                log.info("upload form name:{}", formName);
                                log.info("upload file name:{}", filename);

                                // handle file upload
                                Flux<DataBuffer> contents = partEvents.map(PartEvent::content);
                                var fileBytes = DataBufferUtils.join(contents)
                                        .map(dataBuffer -> {
                                            byte[] bytes = new byte[dataBuffer.readableByteCount()];
                                            dataBuffer.read(bytes);
                                            DataBufferUtils.release(dataBuffer);
                                            return bytes;
                                        });

                                return Mono.just(filename);
                            } else
                                // no value
                                return Mono.error(new RuntimeException("Unexpected event: " + event));
                        }

                        log.info("return default flux");
                        //return result;
                        return Flux.empty(); // either complete or error signal
                    });
                });

        return ok().body(result);
    }
}
