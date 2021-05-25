/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.solace.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class PolledConsumerApplication {

	/*
	 * Inject a reference to the the Pollable Source as defined in
	 * application.properties
	 */
	@Autowired
	private PollableMessageSource source;

	/*
	 * Used for printing out the current time during polling
	 */
	DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		SpringApplication.run(PolledConsumerApplication.class, args);
	}

	/*
	 * This method polls the queue every 5000ms (5s) for a new message. The number
	 * of messages read during each poll is configured in application.properties.
	 */
	@Scheduled(fixedDelay = 5000)
	public void poll() {
		System.out.println(String.format("Polled at %s", formatter.format(System.currentTimeMillis())));

		// poll for a message. If we receive something, print it to the console
		Boolean result = this.source.poll(receive -> {
			String msg = new String((byte[]) receive.getPayload());
			System.out.println(String.format("Received Message '%s'", msg));
		});

		// There were no messages read during the last poll
		if (!result)
			System.out.println("Nothing to read...");
	}
}
