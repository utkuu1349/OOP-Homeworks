package test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Vector;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.product.JointProduct;
import domain.product.Product;
import domain.product.SimpleProduct;

public class JointProductTest {

	private JointProduct jointproduct;
	private Product[] childproducts = new SimpleProduct[] { new SimpleProduct("ProductA", 100.0f),
			new SimpleProduct("ProductB", 66.8f), new SimpleProduct("ProductC", 40.2f),
			new SimpleProduct("ProductD", 33.4f), new SimpleProduct("ProductE", 11.0f), };

	private String expectedOutput = "JointProduct [name=product name, price=666.48]\n"
			+ "   Product [name=ProductA, price=100.0]\n" + "   Product [name=ProductB, price=66.8]\n"
			+ "   Product [name=ProductC, price=40.2]\n" + "   Product [name=ProductD, price=33.4]\n"
			+ "   Product [name=ProductE, price=11.0]\n";

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("****** BEGIN: JointProduct Unit Test ******");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("****** END: JointProduct Unit Test ******");
	}

	@Before
	public void setUp() throws Exception {
		String name = "product name";
		float price = 666.48f;

		jointproduct = null;
		jointproduct = new JointProduct(name, price);
	}

	@Test
	public void testJointProductConstruction() {
		System.out.println("\n=== Test: Creating a JointProduct  ===");

		String name = "product name";
		float price = 666.48f;
		jointproduct = new JointProduct(name, price);

		assertEquals(name, jointproduct.getName());
		assertEquals(price, jointproduct.getPrice(), 0.00001f);
	}

	@Test
	public void testJointProductAdd_Size() {
		System.out.println("\n=== Test: Adding Products to a JointProduct (Size)  ===");

		for (Product prod : childproducts) {
			jointproduct.addProduct(prod);
		}

		assertEquals(childproducts.length, jointproduct.getProducts().size());
	}

	@Test
	public void testJointProductAdd() {
		System.out.println("\n=== Test: Adding Products to a JointProduct  ===");

		for (Product prod : childproducts) {
			jointproduct.addProduct(prod);
		}

		for (Product prod : jointproduct.getProducts()) {
			assertEquals(true, Arrays.asList(childproducts).contains(prod));
		}
	}

	@Test
	public void testJointProductRemove() {
		System.out.println("\n=== Test: JointProduct Remove ===");

		for (Product prod : childproducts) {
			jointproduct.addProduct(prod);
		}

		Integer[] deleteElements = new Integer[] { 2, 3 };
		Vector<Product> compareRemove = new Vector<>();
		for (int i : deleteElements) {
			jointproduct.removeProduct(childproducts[i]);
			compareRemove.add(childproducts[i]);
		}

		for (Product prod : jointproduct.getProducts()) {
			assertEquals(true, Arrays.asList(childproducts).contains(prod));
		}

		for (Product prod : jointproduct.getProducts()) {
			assertEquals(false, compareRemove.contains(prod));
		}
	}

	@Test
	public void testJointProductToString() {
		System.out.println("\n=== Test: JointProduct toString()  ===");

		for (Product prod : childproducts) {
			jointproduct.addProduct(prod);
		}

		assertEquals(expectedOutput, jointproduct.toString());
	}

}
