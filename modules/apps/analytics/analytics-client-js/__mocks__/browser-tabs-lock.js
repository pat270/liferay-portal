export default jest.fn().mockImplementation(() => {
	return {
		acquireLock: () => Promise.resolve(true),
		releaseLock: () => Promise.resolve(),
	};
});
