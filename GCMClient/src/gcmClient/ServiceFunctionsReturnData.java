package gcmClient;

public class ServiceFunctionsReturnData<T> extends ServiceFunctionsReturn {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
