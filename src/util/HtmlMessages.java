/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import models.Subscription;
import models.User;

/**
 *
 * @author yacin
 */
public class HtmlMessages {
    private String Message ; 
    public HtmlMessages() {
         
        
    }

    public String getMessage() {
        return Message;
    }
    public void setMessage2(User user , Subscription sub){
        this.Message = "<!DOCTYPE html>\n" +
"<html xmlns='http://www.w3.org/1999/xhtml' xmlns:v='urn:schemas-microsoft-com:vml' xmlns:o='urn:schemas-microsoft-com:office:office' lang='en'><head>\n" +
"  <title> Welcome to [Coded Mails] </title>\n" +
"  <!--[if !mso]><!-- -->\n" +
"  <meta http-equiv='X-UA-Compatible' content='IE=edge' />\n" +
"  <!--<![endif]-->\n" +
"  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />\n" +
"  <meta name='viewport' content='width=device-width, initial-scale=1' />\n" +
"  <style type='text/css'>\n" +
"    #outlook a {\n" +
"      padding: 0;\n" +
"    }\n" +
"\n" +
"    body {\n" +
"      margin: 0;\n" +
"      padding: 0;\n" +
"      -webkit-text-size-adjust: 100%;\n" +
"      -ms-text-size-adjust: 100%;\n" +
"    }\n" +
"\n" +
"    table,\n" +
"    td {\n" +
"      border-collapse: collapse;\n" +
"      mso-table-lspace: 0pt;\n" +
"      mso-table-rspace: 0pt;\n" +
"    }\n" +
"\n" +
"    img {\n" +
"      border: 0;\n" +
"      height: auto;\n" +
"      line-height: 100%;\n" +
"      outline: none;\n" +
"      text-decoration: none;\n" +
"      -ms-interpolation-mode: bicubic;\n" +
"    }\n" +
"\n" +
"    p {\n" +
"      display: block;\n" +
"      margin: 13px 0;\n" +
"    }\n" +
"  </style>\n" +
"  <!--[if mso]>\n" +
"        <xml>\n" +
"        <o:OfficeDocumentSettings>\n" +
"          <o:AllowPNG/>\n" +
"          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
"        </o:OfficeDocumentSettings>\n" +
"        </xml>\n" +
"        <![endif]-->\n" +
"  <!--[if lte mso 11]>\n" +
"        <style type='text/css'>\n" +
"          .mj-outlook-group-fix { width:100% !important; }\n" +
"        </style>\n" +
"        <![endif]-->\n" +
"  <!--[if !mso]><!-->\n" +
"  <link href='https://fonts.googleapis.com/css?family=Roboto:100,300,400,700' rel='stylesheet' type='text/css' />\n" +
"  <style type='text/css'>\n" +
"    @import url(https://fonts.googleapis.com/css?family=Roboto:100,300,400,700);\n" +
"  </style>\n" +
"  <!--<![endif]-->\n" +
"  <style type='text/css'>\n" +
"    @media only screen and (min-width:480px) {\n" +
"      .mj-column-per-100 {\n" +
"        width: 100% !important;\n" +
"        max-width: 100%;\n" +
"      }\n" +
"    }\n" +
"  </style>\n" +
"  <style type='text/css'>\n" +
"    @media only screen and (max-width:480px) {\n" +
"      table.mj-full-width-mobile {\n" +
"        width: 100% !important;\n" +
"      }\n" +
"\n" +
"      td.mj-full-width-mobile {\n" +
"        width: auto !important;\n" +
"      }\n" +
"    }\n" +
"  </style>\n" +
"  <style type='text/css'>\n" +
"    a,\n" +
"    span,\n" +
"    td,\n" +
"    th {\n" +
"      -webkit-font-smoothing: antialiased !important;\n" +
"      -moz-osx-font-smoothing: grayscale !important;\n" +
"    }\n" +
"  </style>\n" +
"</head>\n" +
"\n" +
"<body style='background-color:#54595f;'>\n" +
"  <div style='display:none;font-size:1px;color:#ffffff;line-height:1px;max-height:0px;max-width:0px;opacity:0;overflow:hidden;'> Preview - Welcome to Coded Mails </div>\n" +
"  <div style='background-color:#54595f;'>\n" +
"    <!--[if mso | IE]>\n" +
"      <table\n" +
"         align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600'\n" +
"      >\n" +
"        <tr>\n" +
"          <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'>\n" +
"      <![endif]-->\n" +
"    <div style='margin:0px auto;max-width:600px;'>\n" +
"      <table role='presentation' style='width:100%;' cellspacing='0' cellpadding='0' border='0' align='center'>\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td style='direction:ltr;font-size:0px;padding:20px 0;text-align:center;'>\n" +
"              <!--[if mso | IE]>\n" +
"                  <table role='presentation' border='0' cellpadding='0' cellspacing='0'>\n" +
"                \n" +
"        <tr>\n" +
"      \n" +
"            <td\n" +
"               class='' style='vertical-align:top;width:600px;'\n" +
"            >\n" +
"          <![endif]-->\n" +
"              <div class='mj-column-per-100 mj-outlook-group-fix' style='font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'>\n" +
"                <table role='presentation' style='vertical-align:top;' width='100%' cellspacing='0' cellpadding='0' border='0'>\n" +
"                  <tbody><tr>\n" +
"                    <td style='font-size:0px;word-break:break-word;'>\n" +
"                      <!--[if mso | IE]>\n" +
"    \n" +
"        <table role='presentation' border='0' cellpadding='0' cellspacing='0'><tr><td height='20' style='vertical-align:top;height:20px;'>\n" +
"      \n" +
"    <![endif]-->\n" +
"                      <div style='height:20px;'>   </div>\n" +
"                      <!--[if mso | IE]>\n" +
"    \n" +
"        </td></tr></table>\n" +
"      \n" +
"    <![endif]-->\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                </tbody></table>\n" +
"              </div>\n" +
"              <!--[if mso | IE]>\n" +
"            </td>\n" +
"          \n" +
"        </tr>\n" +
"      \n" +
"                  </table>\n" +
"                <![endif]-->\n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    </div>\n" +
"    <!--[if mso | IE]>\n" +
"          </td>\n" +
"        </tr>\n" +
"      </table>\n" +
"      \n" +
"      <table\n" +
"         align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600'\n" +
"      >\n" +
"        <tr>\n" +
"          <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'>\n" +
"      <![endif]-->\n" +
"    <div style='background:#ffffff;background-color:#ffffff;margin:0px auto;border-radius:4px;max-width:600px;'>\n" +
"      <table role='presentation' style='background:#ffffff;background-color:#ffffff;width:100%;border-radius:4px;' cellspacing='0' cellpadding='0' border='0' align='center'>\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td style='direction:ltr;font-size:0px;padding:20px 0;text-align:center;'>\n" +
"              <!--[if mso | IE]>\n" +
"                  <table role='presentation' border='0' cellpadding='0' cellspacing='0'>\n" +
"                \n" +
"        <tr>\n" +
"      \n" +
"            <td\n" +
"               class='' style='vertical-align:top;width:600px;'\n" +
"            >\n" +
"          <![endif]-->\n" +
"              <div class='mj-column-per-100 mj-outlook-group-fix' style='font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'>\n" +
"                <table role='presentation' style='vertical-align:top;' width='100%' cellspacing='0' cellpadding='0' border='0'>\n" +
"                  <tbody><tr>\n" +
"                    <td style='font-size:0px;padding:8px 0;word-break:break-word;' align='center'>\n" +
"                      <table role='presentation' style='border-collapse:collapse;border-spacing:0px;' cellspacing='0' cellpadding='0' border='0'>\n" +
"                        <tbody>\n" +
"                          <tr>\n" +
"                            <td style='width:150px;'>\n" +
"                              <img src='https://codedmails.com/images/logo.png' style='border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;' width='150' height='auto' />\n" +
"                            </td>\n" +
"                          </tr>\n" +
"                        </tbody>\n" +
"                      </table>\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td style='font-size:0px;padding:10px 25px;word-break:break-word;'>\n" +
"                      <p style='border-top:dashed 1px lightgrey;font-size:1px;margin:0px auto;width:100%;'>\n" +
"                      </p>\n" +
"                      <!--[if mso | IE]>\n" +
"        <table\n" +
"           align='center' border='0' cellpadding='0' cellspacing='0' style='border-top:dashed 1px lightgrey;font-size:1px;margin:0px auto;width:550px;' role='presentation' width='550px'\n" +
"        >\n" +
"          <tr>\n" +
"            <td style='height:0;line-height:0;'>\n" +
"              &nbsp;\n" +
"            </td>\n" +
"          </tr>\n" +
"        </table>\n" +
"      <![endif]-->\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td style='font-size:0px;padding:10px 25px;word-break:break-word;' align='left'>\n" +
"                      <div style='font-family:Roboto, Helvetica, Arial, sans-serif;font-size:24px;font-weight:300;line-height:30px;text-align:left;color:#000000;'>Welcome to fitHealth Center</div>\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td style='font-size:0px;padding:10px 25px;word-break:break-word;' align='left'>\n" +
"                      <div style='font-family:Roboto, Helvetica, Arial, sans-serif;font-size:14px;font-weight:300;line-height:20px;text-align:left;color:#000000;'>"
                + "Good Morning !  <a href='#' style='color: #2e58ff; text-decoration: none; font-weight: 400;'>"+user.getName()+"</a>."
                + " You have recieved New subscription "+sub.getReference()+" it will start at  \n" +sub.getDatesub().toString() +
"                            and will expired at "+sub.getDateExpire().toString() +".</div>\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td vertical-align='middle' style='font-size:0px;padding:10px 25px;word-break:break-word;' align='center'>\n" +
"                      <table role='presentation' style='border-collapse:separate;line-height:100%;' cellspacing='0' cellpadding='0' border='0'>\n" +
"                        <tbody><tr>\n" +
"                          <td role='presentation' style='border:none;border-radius:3px;cursor:auto;mso-padding-alt:8px 16px;background:#54595f;' valign='middle' bgcolor='#54595f' align='center'>\n" +
"                            <a href='https://google.com' style='display: inline-block; background: #54595f; color: white; font-family: Roboto, Helvetica, Arial, sans-serif; font-size: 13px; font-weight: normal; line-height: 20px; margin: 0; text-decoration: none; text-transform: none; padding: 8px 16px; mso-padding-alt: 0px; border-radius: 3px;' target='_blank'> Check Our WebSite </a>\n" +
"                          </td>\n" +
"                        </tr>\n" +
"                      </tbody></table>\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td style='font-size:0px;padding:10px 25px;word-break:break-word;' align='left'>\n" +
"                      <div style='font-family:Roboto, Helvetica, Arial, sans-serif;font-size:14px;font-weight:300;line-height:20px;text-align:left;color:#000000;'>If you have any questions simply reply to this email and we would be more than happy to reply. :)</div>\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                </tbody></table>\n" +
"              </div>\n" +
"              <!--[if mso | IE]>\n" +
"            </td>\n" +
"          \n" +
"        </tr>\n" +
"      \n" +
"                  </table>\n" +
"                <![endif]-->\n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    </div>\n" +
"    <!--[if mso | IE]>\n" +
"          </td>\n" +
"        </tr>\n" +
"      </table>\n" +
"      \n" +
"      <table\n" +
"         align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600'\n" +
"      >\n" +
"        <tr>\n" +
"          <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'>\n" +
"      <![endif]-->\n" +
"    <div style='margin:0px auto;max-width:600px;'>\n" +
"      <table role='presentation' style='width:100%;' cellspacing='0' cellpadding='0' border='0' align='center'>\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td style='direction:ltr;font-size:0px;padding:20px 0;text-align:center;'>\n" +
"              <!--[if mso | IE]>\n" +
"                  <table role='presentation' border='0' cellpadding='0' cellspacing='0'>\n" +
"                \n" +
"        <tr>\n" +
"      \n" +
"            <td\n" +
"               class='' style='vertical-align:top;width:600px;'\n" +
"            >\n" +
"          <![endif]-->\n" +
"              <div class='mj-column-per-100 mj-outlook-group-fix' style='font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'>\n" +
"                <table role='presentation' style='vertical-align:top;' width='100%' cellspacing='0' cellpadding='0' border='0'>\n" +
"                  <tbody><tr>\n" +
"                    <td style='font-size:0px;padding:10px 25px;word-break:break-word;' align='center'>\n" +
"                      <div style='font-family:Roboto, Helvetica, Arial, sans-serif;font-size:14px;font-weight:300;line-height:20px;text-align:center;color:#fafafa;'>\n" +
"                        <!-- <a href='#' style='color:#fafafa'>Privacy Policy</a> | <a href='#' style='color:#fafafa'>Contact Support</a> | <a href='#' style='color:#fafafa'>Unsubscribe</a><br /> --> 123 Medalling Jr., Suite 100, Parrot Park, CA 12345<br /> © 2020 [Coded Mails] Inc.</div>\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td style='font-size:0px;padding:10px 25px;word-break:break-word;' align='center'>\n" +
"                      <div style='font-family:Roboto, Helvetica, Arial, sans-serif;font-size:14px;font-weight:300;line-height:20px;text-align:center;color:#fafafa;'>Update your <a class='footer-link' href='https://google.com' style='color: #2e58ff; text-decoration: none; font-weight: 400;'>email preferences</a> to choose the types of emails you receive, or you can <a class='footer-link' href='https://google.com' style='color: #2e58ff; text-decoration: none; font-weight: 400;'> unsubscribe </a>from all future emails.</div>\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                  <tr>\n" +
"                    <td style='font-size:0px;padding:10px 25px;word-break:break-word;' align='center'>\n" +
"                      <!--[if mso | IE]>\n" +
"      <table\n" +
"         align='center' border='0' cellpadding='0' cellspacing='0' role='presentation'\n" +
"      >\n" +
"        <tr>\n" +
"      \n" +
"              <td>\n" +
"            <![endif]-->\n" +
"                      <table role='presentation' style='float:none;display:inline-table;' cellspacing='0' cellpadding='0' border='0' align='center'>\n" +
"                        <tbody><tr>\n" +
"                          <td style='padding:4px;'>\n" +
"                            <table role='presentation' style='border-radius:3px;width:24px;' cellspacing='0' cellpadding='0' border='0'>\n" +
"                              <tbody><tr>\n" +
"                                <td style='font-size:0;height:24px;vertical-align:middle;width:24px;'>\n" +
"                                  <a href='#' target='_blank' style='color: #2e58ff; text-decoration: none; font-weight: 400;'>\n" +
"                                    <img alt='twitter-logo' src='https://codedmails.com/images/social/white/twitter-logo-transparent-white.png' style='border-radius:3px;display:block;' width='24' height='24' />\n" +
"                                  </a>\n" +
"                                </td>\n" +
"                              </tr>\n" +
"                            </tbody></table>\n" +
"                          </td>\n" +
"                        </tr>\n" +
"                      </tbody></table>\n" +
"                      <!--[if mso | IE]>\n" +
"              </td>\n" +
"            \n" +
"              <td>\n" +
"            <![endif]-->\n" +
"                      <table role='presentation' style='float:none;display:inline-table;' cellspacing='0' cellpadding='0' border='0' align='center'>\n" +
"                        <tbody><tr>\n" +
"                          <td style='padding:4px;'>\n" +
"                            <table role='presentation' style='border-radius:3px;width:24px;' cellspacing='0' cellpadding='0' border='0'>\n" +
"                              <tbody><tr>\n" +
"                                <td style='font-size:0;height:24px;vertical-align:middle;width:24px;'>\n" +
"                                  <a href='#' target='_blank' style='color: #2e58ff; text-decoration: none; font-weight: 400;'>\n" +
"                                    <img alt='facebook-logo' src='https://codedmails.com/images/social/white/facebook-logo-transparent-white.png' style='border-radius:3px;display:block;' width='24' height='24' />\n" +
"                                  </a>\n" +
"                                </td>\n" +
"                              </tr>\n" +
"                            </tbody></table>\n" +
"                          </td>\n" +
"                        </tr>\n" +
"                      </tbody></table>\n" +
"                      <!--[if mso | IE]>\n" +
"              </td>\n" +
"            \n" +
"              <td>\n" +
"            <![endif]-->\n" +
"                      <table role='presentation' style='float:none;display:inline-table;' cellspacing='0' cellpadding='0' border='0' align='center'>\n" +
"                        <tbody><tr>\n" +
"                          <td style='padding:4px;'>\n" +
"                            <table role='presentation' style='border-radius:3px;width:24px;' cellspacing='0' cellpadding='0' border='0'>\n" +
"                              <tbody><tr>\n" +
"                                <td style='font-size:0;height:24px;vertical-align:middle;width:24px;'>\n" +
"                                  <a href='#' target='_blank' style='color: #2e58ff; text-decoration: none; font-weight: 400;'>\n" +
"                                    <img alt='instagram-logo' src='https://codedmails.com/images/social/white/instagram-logo-transparent-white.png' style='border-radius:3px;display:block;' width='24' height='24' />\n" +
"                                  </a>\n" +
"                                </td>\n" +
"                              </tr>\n" +
"                            </tbody></table>\n" +
"                          </td>\n" +
"                        </tr>\n" +
"                      </tbody></table>\n" +
"                      <!--[if mso | IE]>\n" +
"              </td>\n" +
"            \n" +
"          </tr>\n" +
"        </table>\n" +
"      <![endif]-->\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                </tbody></table>\n" +
"              </div>\n" +
"              <!--[if mso | IE]>\n" +
"            </td>\n" +
"          \n" +
"        </tr>\n" +
"      \n" +
"                  </table>\n" +
"                <![endif]-->\n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    </div>\n" +
"    <!--[if mso | IE]>\n" +
"          </td>\n" +
"        </tr>\n" +
"      </table>\n" +
"      \n" +
"      <table\n" +
"         align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600'\n" +
"      >\n" +
"        <tr>\n" +
"          <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'>\n" +
"      <![endif]-->\n" +
"    <div style='margin:0px auto;max-width:600px;'>\n" +
"      <table role='presentation' style='width:100%;' cellspacing='0' cellpadding='0' border='0' align='center'>\n" +
"        <tbody>\n" +
"          <tr>\n" +
"            <td style='direction:ltr;font-size:0px;padding:20px 0;text-align:center;'>\n" +
"              <!--[if mso | IE]>\n" +
"                  <table role='presentation' border='0' cellpadding='0' cellspacing='0'>\n" +
"                \n" +
"        <tr>\n" +
"      \n" +
"            <td\n" +
"               class='' style='vertical-align:top;width:600px;'\n" +
"            >\n" +
"          <![endif]-->\n" +
"              <div class='mj-column-per-100 mj-outlook-group-fix' style='font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'>\n" +
"                <table role='presentation' style='vertical-align:top;' width='100%' cellspacing='0' cellpadding='0' border='0'>\n" +
"                  <tbody><tr>\n" +
"                    <td style='font-size:0px;word-break:break-word;'>\n" +
"                      <!--[if mso | IE]>\n" +
"    \n" +
"        <table role='presentation' border='0' cellpadding='0' cellspacing='0'><tr><td height='1' style='vertical-align:top;height:1px;'>\n" +
"      \n" +
"    <![endif]-->\n" +
"                      <div style='height:1px;'>   </div>\n" +
"                      <!--[if mso | IE]>\n" +
"    \n" +
"        </td></tr></table>\n" +
"      \n" +
"    <![endif]-->\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                </tbody></table>\n" +
"              </div>\n" +
"              <!--[if mso | IE]>\n" +
"            </td>\n" +
"          \n" +
"        </tr>\n" +
"      \n" +
"                  </table>\n" +
"                <![endif]-->\n" +
"            </td>\n" +
"          </tr>\n" +
"        </tbody>\n" +
"      </table>\n" +
"    </div>\n" +
"    <!--[if mso | IE]>\n" +
"          </td>\n" +
"        </tr>\n" +
"      </table>\n" +
"      <![endif]-->\n" +
"  </div>\n" +
"\n" +
"\n" +
"</body></html>";
    }
    public void setMessage(Subscription sub , User user) {
        this.Message ="<!doctype html>\n" +
"<html>\n" +
"  <head>\n" +
"    <meta name='viewport' content='width=device-width, initial-scale=1.0'/>\n" +
"    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />\n" +
"    <title>Simple Transactional Email</title>\n" +
"    <style>\n" +
"      /* -------------------------------------\n" +
"          GLOBAL RESETS\n" +
"      ------------------------------------- */\n" +
"      \n" +
"      /*All the styling goes here*/\n" +
"      \n" +
"      img {\n" +
"        border: none;\n" +
"        -ms-interpolation-mode: bicubic;\n" +
"        max-width: 100%; \n" +
"      }\n" +
"\n" +
"      body {\n" +
"        background-color: #f6f6f6;\n" +
"        font-family: sans-serif;\n" +
"        -webkit-font-smoothing: antialiased;\n" +
"        font-size: 14px;\n" +
"        line-height: 1.4;\n" +
"        margin: 0;\n" +
"        padding: 0;\n" +
"        -ms-text-size-adjust: 100%;\n" +
"        -webkit-text-size-adjust: 100%; \n" +
"      }\n" +
"\n" +
"      table {\n" +
"        border-collapse: separate;\n" +
"        mso-table-lspace: 0pt;\n" +
"        mso-table-rspace: 0pt;\n" +
"        width: 100%; }\n" +
"        table td {\n" +
"          font-family: sans-serif;\n" +
"          font-size: 14px;\n" +
"          vertical-align: top; \n" +
"      }\n" +
"\n" +
"      /* -------------------------------------\n" +
"          BODY & CONTAINER\n" +
"      ------------------------------------- */\n" +
"\n" +
"      .body {\n" +
"        background-color: #f6f6f6;\n" +
"        width: 100%; \n" +
"      }\n" +
"\n" +
"      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n" +
"      .container {\n" +
"        display: block;\n" +
"        margin: 0 auto !important;\n" +
"        /* makes it centered */\n" +
"        max-width: 580px;\n" +
"        padding: 10px;\n" +
"        width: 580px; \n" +
"      }\n" +
"\n" +
"      /* This should also be a block element, so that it will fill 100% of the .container */\n" +
"      .content {\n" +
"        box-sizing: border-box;\n" +
"        display: block;\n" +
"        margin: 0 auto;\n" +
"        max-width: 580px;\n" +
"        padding: 10px; \n" +
"      }\n" +
"\n" +
"      /* -------------------------------------\n" +
"          HEADER, FOOTER, MAIN\n" +
"      ------------------------------------- */\n" +
"      .main {\n" +
"        background: #ffffff;\n" +
"        border-radius: 3px;\n" +
"        width: 100%; \n" +
"      }\n" +
"\n" +
"      .wrapper {\n" +
"        box-sizing: border-box;\n" +
"        padding: 20px; \n" +
"      }\n" +
"\n" +
"      .content-block {\n" +
"        padding-bottom: 10px;\n" +
"        padding-top: 10px;\n" +
"      }\n" +
"\n" +
"      .footer {\n" +
"        clear: both;\n" +
"        margin-top: 10px;\n" +
"        text-align: center;\n" +
"        width: 100%; \n" +
"      }\n" +
"        .footer td,\n" +
"        .footer p,\n" +
"        .footer span,\n" +
"        .footer a {\n" +
"          color: #999999;\n" +
"          font-size: 12px;\n" +
"          text-align: center; \n" +
"      }\n" +
"\n" +
"      /* -------------------------------------\n" +
"          TYPOGRAPHY\n" +
"      ------------------------------------- */\n" +
"      h1,\n" +
"      h2,\n" +
"      h3,\n" +
"      h4 {\n" +
"        color: #000000;\n" +
"        font-family: sans-serif;\n" +
"        font-weight: 400;\n" +
"        line-height: 1.4;\n" +
"        margin: 0;\n" +
"        margin-bottom: 30px; \n" +
"      }\n" +
"\n" +
"      h1 {\n" +
"        font-size: 35px;\n" +
"        font-weight: 300;\n" +
"        text-align: center;\n" +
"        text-transform: capitalize; \n" +
"      }\n" +
"\n" +
"      p,\n" +
"      ul,\n" +
"      ol {\n" +
"        font-family: sans-serif;\n" +
"        font-size: 14px;\n" +
"        font-weight: normal;\n" +
"        margin: 0;\n" +
"        margin-bottom: 15px; \n" +
"      }\n" +
"        p li,\n" +
"        ul li,\n" +
"        ol li {\n" +
"          list-style-position: inside;\n" +
"          margin-left: 5px; \n" +
"      }\n" +
"\n" +
"      a {\n" +
"        color: #3498db;\n" +
"        text-decoration: underline; \n" +
"      }\n" +
"\n" +
"      /* -------------------------------------\n" +
"          BUTTONS\n" +
"      ------------------------------------- */\n" +
"      .btn {\n" +
"        box-sizing: border-box;\n" +
"        width: 100%; }\n" +
"        .btn > tbody > tr > td {\n" +
"          padding-bottom: 15px; }\n" +
"        .btn table {\n" +
"          width: auto; \n" +
"      }\n" +
"        .btn table td {\n" +
"          background-color: #ffffff;\n" +
"          border-radius: 5px;\n" +
"          text-align: center; \n" +
"      }\n" +
"        .btn a {\n" +
"          background-color: #ffffff;\n" +
"          border: solid 1px #3498db;\n" +
"          border-radius: 5px;\n" +
"          box-sizing: border-box;\n" +
"          color: #3498db;\n" +
"          cursor: pointer;\n" +
"          display: inline-block;\n" +
"          font-size: 14px;\n" +
"          font-weight: bold;\n" +
"          margin: 0;\n" +
"          padding: 12px 25px;\n" +
"          text-decoration: none;\n" +
"          text-transform: capitalize; \n" +
"      }\n" +
"\n" +
"      .btn-primary table td {\n" +
"        background-color: #3498db; \n" +
"      }\n" +
"\n" +
"      .btn-primary a {\n" +
"        background-color: #3498db;\n" +
"        border-color: #3498db;\n" +
"        color: #ffffff; \n" +
"      }\n" +
"\n" +
"      /* -------------------------------------\n" +
"          OTHER STYLES THAT MIGHT BE USEFUL\n" +
"      ------------------------------------- */\n" +
"      .last {\n" +
"        margin-bottom: 0; \n" +
"      }\n" +
"\n" +
"      .first {\n" +
"        margin-top: 0; \n" +
"      }\n" +
"\n" +
"      .align-center {\n" +
"        text-align: center; \n" +
"      }\n" +
"\n" +
"      .align-right {\n" +
"        text-align: right; \n" +
"      }\n" +
"\n" +
"      .align-left {\n" +
"        text-align: left; \n" +
"      }\n" +
"\n" +
"      .clear {\n" +
"        clear: both; \n" +
"      }\n" +
"\n" +
"      .mt0 {\n" +
"        margin-top: 0; \n" +
"      }\n" +
"\n" +
"      .mb0 {\n" +
"        margin-bottom: 0; \n" +
"      }\n" +
"\n" +
"      .preheader {\n" +
"        color: transparent;\n" +
"        display: none;\n" +
"        height: 0;\n" +
"        max-height: 0;\n" +
"        max-width: 0;\n" +
"        opacity: 0;\n" +
"        overflow: hidden;\n" +
"        mso-hide: all;\n" +
"        visibility: hidden;\n" +
"        width: 0; \n" +
"      }\n" +
"\n" +
"      .powered-by a {\n" +
"        text-decoration: none; \n" +
"      }\n" +
"\n" +
"      hr {\n" +
"        border: 0;\n" +
"        border-bottom: 1px solid #f6f6f6;\n" +
"        margin: 20px 0; \n" +
"      }\n" +
"\n" +
"      /* -------------------------------------\n" +
"          RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
"      ------------------------------------- */\n" +
"      @media only screen and (max-width: 620px) {\n" +
"        table.body h1 {\n" +
"          font-size: 28px !important;\n" +
"          margin-bottom: 10px !important; \n" +
"        }\n" +
"        table.body p,\n" +
"        table.body ul,\n" +
"        table.body ol,\n" +
"        table.body td,\n" +
"        table.body span,\n" +
"        table.body a {\n" +
"          font-size: 16px !important; \n" +
"        }\n" +
"        table.body .wrapper,\n" +
"        table.body .article {\n" +
"          padding: 10px !important; \n" +
"        }\n" +
"        table.body .content {\n" +
"          padding: 0 !important; \n" +
"        }\n" +
"        table.body .container {\n" +
"          padding: 0 !important;\n" +
"          width: 100% !important; \n" +
"        }\n" +
"        table.body .main {\n" +
"          border-left-width: 0 !important;\n" +
"          border-radius: 0 !important;\n" +
"          border-right-width: 0 !important; \n" +
"        }\n" +
"        table.body .btn table {\n" +
"          width: 100% !important; \n" +
"        }\n" +
"        table.body .btn a {\n" +
"          width: 100% !important; \n" +
"        }\n" +
"        table.body .img-responsive {\n" +
"          height: auto !important;\n" +
"          max-width: 100% !important;\n" +
"          width: auto !important; \n" +
"        }\n" +
"      }\n" +
"\n" +
"      /* -------------------------------------\n" +
"          PRESERVE THESE STYLES IN THE HEAD\n" +
"      ------------------------------------- */\n" +
"      @media all {\n" +
"        .ExternalClass {\n" +
"          width: 100%; \n" +
"        }\n" +
"        .ExternalClass,\n" +
"        .ExternalClass p,\n" +
"        .ExternalClass span,\n" +
"        .ExternalClass font,\n" +
"        .ExternalClass td,\n" +
"        .ExternalClass div {\n" +
"          line-height: 100%; \n" +
"        }\n" +
"        .apple-link a {\n" +
"          color: inherit !important;\n" +
"          font-family: inherit !important;\n" +
"          font-size: inherit !important;\n" +
"          font-weight: inherit !important;\n" +
"          line-height: inherit !important;\n" +
"          text-decoration: none !important; \n" +
"        }\n" +
"        #MessageViewBody a {\n" +
"          color: inherit;\n" +
"          text-decoration: none;\n" +
"          font-size: inherit;\n" +
"          font-family: inherit;\n" +
"          font-weight: inherit;\n" +
"          line-height: inherit;\n" +
"        }\n" +
"        .btn-primary table td:hover {\n" +
"          background-color: #34495e !important; \n" +
"        }\n" +
"        .btn-primary a:hover {\n" +
"          background-color: #34495e !important;\n" +
"          border-color: #34495e !important; \n" +
"        } \n" +
"      }\n" +
"\n" +
"    </style>\n" +
"  </head>\n" +
"  <body>\n" +
"    <span class='preheader'>FIT-HEALTH officiel Email</span>\n" +
"    <table role='presentation' border='0' cellpadding='0' cellspacing='0' class='body'>\n" +
"      <tr>\n" +
"        <td>&nbsp;</td>\n" +
"        <td class='container'>\n" +
"          <div class='content'>\n" +
"\n" +
"            <!-- START CENTERED WHITE CONTAINER -->\n" +
"            <table role='presentation' class='main'>\n" +
"\n" +
"              <!-- START MAIN CONTENT AREA -->\n" +
"              <tr>\n" +
"                <td class='wrapper'>\n" +
"                  <table role='presentation' border='0' cellpadding='0' cellspacing='0'>\n" +
"                    <tr>\n" +
"                      <td>\n" +
"                        <p>Hi "+user.getName()+",</p>\n" +
"                        <p>This mail just to inform you recieved new Sub "+sub.getReference()+" it will start at  \n" +sub.getDatesub().toString() +
"                            and will expired at "+sub.getDateExpire().toString() +"</p>\n" +
"                        \n" +
"                        <p>Don't replay this mail its auto mail.</p>\n" +
"                        <p>Good luck! Hope it works.</p>\n" +
"                      </td>\n" +
"                    </tr>\n" +
"                  </table>\n" +
"                </td>\n" +
"              </tr>\n" +
"\n" +
"            <!-- END MAIN CONTENT AREA -->\n" +
"            </table>\n" +
"            <!-- END CENTERED WHITE CONTAINER -->\n" +
"\n" +
"            <!-- START FOOTER -->\n" +
"            <div class='footer'>\n" +
"              <table role='presentation' border='0' cellpadding='0' cellspacing='0'>\n" +
"                <tr>\n" +
"                  <td class='content-block'>\n" +
"                    <span class='apple-link'>Esprit Ghazella FitHealth GRP</span>\n" +
"                   \n" +
"                  </td>\n" +
"                </tr>\n" +
"                <tr>\n" +
"                  <td class='content-block powered-by'>\n" +
"                    Powered by <a href='http://htmlemail.io'>Yessine ben salah</a>.\n" +
"                  </td>\n" +
"                </tr>\n" +
"              </table>\n" +
"            </div>\n" +
"            <!-- END FOOTER -->\n" +
"\n" +
"          </div>\n" +
"        </td>\n" +
"        <td>&nbsp;</td>\n" +
"      </tr>\n" +
"    </table>\n" +
"  </body>\n" +
"</html>"; 
    }
    
}
