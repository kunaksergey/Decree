package ua.shield.jsf.controller;

import com.itextpdf.text.DocumentException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import ua.shield.entity.Order;
import ua.shield.entity.Sign;
import ua.shield.entity.User;
import ua.shield.helper.PdfCreatorForOrder;
import ua.shield.jsf.exeption.UserIsNotRegisteredExeption;
import ua.shield.service.OrderService;
import ua.shield.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sa on 17.07.17.
 */
@ManagedBean
@SessionScoped
public class OrderJsfController {

    @ManagedProperty("#{jpaOrderService}")
    OrderService jpaOrderService;

    @ManagedProperty("#{jpaUserService}")
    private UserService jpaUserService;

    @ManagedProperty("#{securityController}")
    private SecurityController securityController;


    Order selectedOrder;


    public OrderService getJpaOrderService() {
        return jpaOrderService;
    }

    public void setJpaOrderService(OrderService jpaOrderService) {
        this.jpaOrderService = jpaOrderService;
    }

    public UserService getJpaUserService() {
        return jpaUserService;
    }

    public void setJpaUserService(UserService jpaUserService) {
        this.jpaUserService = jpaUserService;
    }

    public SecurityController getSecurityController() {
        return securityController;
    }

    public void setSecurityController(SecurityController securityController) {
        this.securityController = securityController;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }


    public List<Order> getOrderListWithStatusPrepared() {
        try {
            User owner = jpaUserService.registeredUser();
            return jpaOrderService.findAllByOwner(owner).stream()
                    .filter(d -> d.getStatus() == Order.STATUS_PREPARED).collect(Collectors.toList());
        } catch (UserIsNotRegisteredExeption userIsNotRegisteredExeption) {
            return new ArrayList<>();
        }
    }

    public List<Order> getOrderListWithStatusInWork() {
        return jpaOrderService.findAll().stream().filter(d -> d.getStatus() == Order.STATUS_IN_WORK).collect(Collectors.toList());
    }

    public List<Order> getOrderListWithStatusCompleted() {
        return jpaOrderService.findAll().stream().filter(d -> d.getStatus() == Order.STATUS_COMPLETED).collect(Collectors.toList());
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Save status an order like in work
     */
    public void saveStatusToWork() {
        selectedOrder.setStatus(Order.STATUS_IN_WORK);
        jpaOrderService.save(selectedOrder);
    }

    /**
     * Save status an order like completed
     */
    public void saveStatusToComplited() {
        selectedOrder.setStatus(Order.STATUS_COMPLETED);
        jpaOrderService.save(selectedOrder);
    }


    /**
     * add sign current user to order
     */
    public void addSign() {
        try {
            User currentUser = securityController.getRegisteredUser();
            if (hasUserSign(currentUser) && !isSignByUser(currentUser)) {
                addSign(currentUser);
                jpaOrderService.save(selectedOrder);
            }
            if (hasAllSignes()) {
                saveStatusToComplited();
            }
        } catch (UserIsNotRegisteredExeption userIsNotRegisteredExeption) {

        }

    }

    /**
     * add sign any user to order
     *
     * @user any user
     */
    public void addSign(User user) {
        Optional<Sign> signOptional = selectedOrder.getSetSign().stream().filter(sing -> sing.getUser().equals(user)).findFirst();
        Sign sign = signOptional.get();
        sign.setSign(true);
        sign.setDateTimeSign(LocalDateTime.now());
    }

    /**
     * Does it have my sign in the list
     *
     * @return true-it has, false-no
     */

    public boolean hasCurrentUserSign() {
        try {
            User currentUser = jpaUserService.registeredUser();
            return hasUserSign(currentUser);
        } catch (UserIsNotRegisteredExeption userIsNotRegisteredExeption) {
            return false;
        }
    }

    /**
     * Does it have sign of @user
     *
     * @return true-it has, false-no
     */
    public boolean hasUserSign(User user) {
        return selectedOrder.getSetSign().stream()
                .anyMatch(sign -> sign.getUser().equals(user));
    }


    /**
     * has it been signed by me
     *
     * @return true-yes, false -no
     */
    public boolean isSignByCurrentUser() {
        try {
            return isSignByUser(jpaUserService.registeredUser());
        } catch (UserIsNotRegisteredExeption userIsNotRegisteredExeption) {
            return false;
        }

    }

    /**
     * has it been signed by user
     *
     * @return true-yes, false -no
     */
    public boolean isSignByUser(User user) {
        return selectedOrder.getSetSign().stream()
                .anyMatch(sign -> sign.getUser().equals(user)
                        && sign.isSign());
    }

    public boolean hasAllSignes() {
        return selectedOrder.getSetSign().stream()
                .allMatch(sign -> sign.getDateTimeSign() != null);
    }

    public void selectOrder(String orderId) {
        System.out.println(orderId);
        int id = Integer.parseInt(orderId);
        this.selectedOrder = jpaOrderService.findById(id);
        System.out.println(selectedOrder);
    }

    /**
     * download information like a pdf file
     *
     * @return
     */
    public StreamedContent downloadOrderPdf() throws DocumentException, IOException {
        ByteArrayOutputStream byteArrayOutputStreamOfOrder = new PdfCreatorForOrder(selectedOrder).pdfToByteArrayOutputStream();
        InputStream stream = new ByteArrayInputStream(byteArrayOutputStreamOfOrder.toByteArray());
        StreamedContent streamContent = new DefaultStreamedContent(stream, "image/pdf", "order.pdf");
        return streamContent;
    }

}
