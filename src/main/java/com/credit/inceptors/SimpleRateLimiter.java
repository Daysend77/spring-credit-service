package com.credit.inceptors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SimpleRateLimiter {
	private Semaphore semaphore;
	private int claimLimit;
	private TimeUnit timePeriod = TimeUnit.SECONDS;
	private ScheduledExecutorService scheduledExecutorService;

	public static SimpleRateLimiter create(int claimLimit) {
		SimpleRateLimiter limiter = new SimpleRateLimiter(claimLimit);
		limiter.schedulePermitReplenishment();
		return limiter;
	}

	private SimpleRateLimiter(int claimLimit) {
		this.semaphore = new Semaphore(claimLimit);
		this.claimLimit = claimLimit;
	}

	public boolean tryAcquire() {
		return semaphore.tryAcquire();
	}

	public void stop() {
		scheduledExecutorService.shutdownNow();
	}

	public void schedulePermitReplenishment() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.schedule(() -> {
			semaphore.release(claimLimit); }, 60,  timePeriod);

	}
}