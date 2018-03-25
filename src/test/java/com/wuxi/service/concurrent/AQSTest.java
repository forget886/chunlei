package com.wuxi.service.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AQSTest extends AbstractQueuedSynchronizer{

	@Override
	protected boolean tryAcquire(int arg) {
		return super.tryAcquire(arg);
	}

	@Override
	protected boolean tryRelease(int arg) {
		return super.tryRelease(arg);
	}

	@Override
	protected int tryAcquireShared(int arg) {
		return super.tryAcquireShared(arg);
	}

	@Override
	protected boolean tryReleaseShared(int arg) {
		return super.tryReleaseShared(arg);
	}

	
}
