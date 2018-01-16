package com.yuanfy.test.enums;

public enum Light implements Color{
	//在实例中可以重写Enum和当前类中的定义的方法。
	RED, BLUE, YELLOW{
		@Override
		public void colorChange() {
			super.colorChange();
		}
		@Override
		public void start() {
			super.start();
		}
		@Override
		public String toString() {
			return super.toString();
		}
	};
	public void start(){TestEnum t = TestEnum.Test;}
	@Override
	public void colorChange() {}
	/**
	 * 可以重写Enum的方法
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
